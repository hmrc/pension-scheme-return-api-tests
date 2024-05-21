/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.api.specs

import uk.gov.hmrc.test.api.service.{PensionSchemeReturnOverviewService, PensionSchemeReturnPOSTRequestService}


class PensionSchemeReturnPOSTRequestsSpec extends BaseSpec {

  val pensionSchemeReturnPOSTServiceAPI: PensionSchemeReturnPOSTRequestService = new PensionSchemeReturnPOSTRequestService
  val authBearerToken: String = authHelper.getAuthBearerToken
  val payload: String =
    """
      |{
      |    "minimalRequiredSubmission": {
      |        "reportDetails": {
      |            "pstr": "00000042IN",
      |            "periodStart": "2023-04-06",
      |            "periodEnd": "2024-04-05"
      |        },
      |        "accountingPeriods": [
      |            [
      |                "2023-04-06",
      |                "2024-04-05"
      |            ]
      |        ],
      |        "schemeDesignatory": {
      |            "openBankAccount": true,
      |            "activeMembers": 23,
      |            "deferredMembers": 45,
      |            "pensionerMembers": 6,
      |            "totalPayments": 74
      |        }
      |    },
      |    "checkReturnDates": true,
      |    "shares": {
      |        "optShareTransactions": [
      |            {
      |                "typeOfSharesHeld": "01",
      |                "shareIdentification": {
      |                    "nameOfSharesCompany": "AppleSauce Inc.",
      |                    "optReasonNoCRN": "Not able to locate Company on Companies House",
      |                    "classOfShares": "Ordinary Shares"
      |                },
      |                "heldSharesTransaction": {
      |                    "schemeHoldShare": "01",
      |                    "optDateOfAcqOrContrib": "2022-10-29",
      |                    "totalShares": 200,
      |                    "optAcquiredFromName": "Fredd Bloggs",
      |                    "optPropertyAcquiredFrom": {
      |                        "identityType": "individual",
      |                        "idNumber": "JE123176A"
      |                    },
      |                    "optConnectedPartyStatus": false,
      |                    "costOfShares": 10000,
      |                    "supportedByIndepValuation": true,
      |                    "optTotalAssetValue": 2000,
      |                    "totalDividendsOrReceipts": 500
      |                },
      |                "optDisposedSharesTransaction": [
      |                    {
      |                        "methodOfDisposal": "Sold",
      |                        "optSalesQuestions": {
      |                            "dateOfSale": "2023-04-06",
      |                            "noOfSharesSold": 4,
      |                            "amountReceived": 38.3,
      |                            "nameOfPurchaser": "nameOfPurchaser",
      |                            "purchaserType": {
      |                                "identityType": "individual",
      |                                "reasonNoIdNumber": "sdfsdf"
      |                            },
      |                            "connectedPartyStatus": false,
      |                            "supportedByIndepValuation": true
      |                        },
      |                        "totalSharesNowHeld": 1
      |                    },
      |                    {
      |                        "methodOfDisposal": "Redeemed",
      |                        "optRedemptionQuestions": {
      |                            "dateOfRedemption": "2023-03-07",
      |                            "noOfSharesRedeemed": 27,
      |                            "amountReceived": 1907
      |                        },
      |                        "totalSharesNowHeld": 1
      |                    }
      |                ]
      |            },
      |            {
      |                "typeOfSharesHeld": "03",
      |                "shareIdentification": {
      |                    "nameOfSharesCompany": "Pear Computers Inc.",
      |                    "optCrnNumber": "LP289157",
      |                    "classOfShares": "Preferred Shares"
      |                },
      |                "heldSharesTransaction": {
      |                    "schemeHoldShare": "01",
      |                    "optDateOfAcqOrContrib": "2023-02-23",
      |                    "totalShares": 10000,
      |                    "optAcquiredFromName": "Golden Investments Ltd.",
      |                    "optPropertyAcquiredFrom": {
      |                        "identityType": "ukPartnership",
      |                        "idNumber": "28130262"
      |                    },
      |                    "optConnectedPartyStatus": false,
      |                    "costOfShares": 50000,
      |                    "supportedByIndepValuation": true,
      |                    "optTotalAssetValue": 40000,
      |                    "totalDividendsOrReceipts": 200
      |                },
      |                "optDisposedSharesTransaction": [
      |                    {
      |                        "methodOfDisposal": "Transferred",
      |                        "totalSharesNowHeld": 48
      |                    },
      |                    {
      |                        "methodOfDisposal": "Other",
      |                        "totalSharesNowHeld": 27
      |                    }
      |                ]
      |            },
      |            {
      |                "typeOfSharesHeld": "03",
      |                "shareIdentification": {
      |                    "nameOfSharesCompany": "Connected Party Inc.",
      |                    "optCrnNumber": "LP289157",
      |                    "classOfShares": "Convertible Preference Shares"
      |                },
      |                "heldSharesTransaction": {
      |                    "schemeHoldShare": "02",
      |                    "optDateOfAcqOrContrib": "2023-02-23",
      |                    "totalShares": 1000,
      |                    "optAcquiredFromName": "Investec Inc.",
      |                    "optPropertyAcquiredFrom": {
      |                        "identityType": "ukCompany",
      |                        "idNumber": "0000123456"
      |                    },
      |                    "optConnectedPartyStatus": false,
      |                    "costOfShares": 120220.34,
      |                    "supportedByIndepValuation": true,
      |                    "optTotalAssetValue": 10000,
      |                    "totalDividendsOrReceipts": 599.99
      |                }
      |            }
      |        ],
      |        "optTotalValueQuotedShares": 12.34
      |    }
      |}
      |"""
      .stripMargin

  Feature("POST Requests for Standard PSR API") {

    Scenario("Verify 204 response when POST request to create scheme endpoint") {
      When("the Not Found endpoint is called the response should be 204")
      POSTRequestsHelper.postFullBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

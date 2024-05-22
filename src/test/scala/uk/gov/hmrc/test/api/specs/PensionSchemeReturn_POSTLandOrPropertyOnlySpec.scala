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

import uk.gov.hmrc.test.api.service.PensionSchemeReturnPOSTRequestService


class PensionSchemeReturn_POSTLandOrPropertyOnlySpec extends BaseSpec {

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
      |    "assets": {
      |        "optLandOrProperty": {
      |            "landOrPropertyHeld": true,
      |            "disposeAnyLandOrProperty": true,
      |            "landOrPropertyTransactions": [
      |                {
      |                    "propertyDetails": {
      |                        "landOrPropertyInUK": false,
      |                        "addressDetails": {
      |                            "addressLine1": "Fenerbahce",
      |                            "addressLine3": "Kadikoy",
      |                            "town": "Istanbul",
      |                            "countryCode": "TR"
      |                        },
      |                        "landRegistryTitleNumberKey": false,
      |                        "landRegistryTitleNumberValue": "Foreign property"
      |                    },
      |                    "heldPropertyTransaction": {
      |                        "methodOfHolding": "Acquisition",
      |                        "dateOfAcquisitionOrContribution": "1953-03-28",
      |                        "optPropertyAcquiredFromName": "Taylor Wonky Housing Estates Ltd",
      |                        "optPropertyAcquiredFrom": {
      |                            "identityType": "individual",
      |                            "idNumber": "SX123456A"
      |                        },
      |                        "optConnectedPartyStatus": true,
      |                        "totalCostOfLandOrProperty": 1000000,
      |                        "optIndepValuationSupport": true,
      |                        "isLandOrPropertyResidential": true,
      |                        "landOrPropertyLeased": false,
      |                        "totalIncomeOrReceipts": 25000
      |                    },
      |                    "optDisposedPropertyTransaction": [
      |                        {
      |                            "methodOfDisposal": "Sold",
      |                            "optDateOfSale": "2022-10-19",
      |                            "optNameOfPurchaser": "Victor Enterprises Inc.",
      |                            "optPropertyAcquiredFrom": {
      |                                "identityType": "ukCompany",
      |                                "idNumber": "24896221"
      |                            },
      |                            "optSaleProceeds": 1500000,
      |                            "optConnectedPartyStatus": true,
      |                            "optIndepValuationSupport": false,
      |                            "portionStillHeld": false
      |                        }
      |                    ]
      |                },
      |                {
      |                    "propertyDetails": {
      |                        "landOrPropertyInUK": true,
      |                        "addressDetails": {
      |                            "addressLine1": "Beyoglu",
      |                            "addressLine2": "Ulker Arena",
      |                            "addressLine3": "Kadikoy",
      |                            "town": "Istanbul",
      |                            "postCode": "GB135HG",
      |                            "countryCode": "GB"
      |                        },
      |                        "landRegistryTitleNumberKey": true,
      |                        "landRegistryTitleNumberValue": "LR10000102202202"
      |                    },
      |                    "heldPropertyTransaction": {
      |                        "methodOfHolding": "Contribution",
      |                        "dateOfAcquisitionOrContribution": "1953-03-28",
      |                        "optPropertyAcquiredFromName": "Taylor Wonky Housing Estates Ltd.",
      |                        "optPropertyAcquiredFrom": {
      |                            "identityType": "individual",
      |                            "idNumber": "SX123456A"
      |                        },
      |                        "optConnectedPartyStatus": true,
      |                        "totalCostOfLandOrProperty": 1000000,
      |                        "optIndepValuationSupport": false,
      |                        "isLandOrPropertyResidential": true,
      |                        "landOrPropertyLeased": false,
      |                        "totalIncomeOrReceipts": 25000
      |                    },
      |                    "optDisposedPropertyTransaction": [
      |                        {
      |                            "methodOfDisposal": "Sold",
      |                            "optDateOfSale": "2022-10-19",
      |                            "optNameOfPurchaser": "Victor Enterprises Inc.",
      |                            "optPropertyAcquiredFrom": {
      |                                "identityType": "ukCompany",
      |                                "idNumber": "24896221"
      |                            },
      |                            "optSaleProceeds": 1500000,
      |                            "optConnectedPartyStatus": true,
      |                            "optIndepValuationSupport": false,
      |                            "portionStillHeld": false
      |                        }
      |                    ]
      |                },
      |                {
      |                    "propertyDetails": {
      |                        "landOrPropertyInUK": false,
      |                        "addressDetails": {
      |                            "addressLine1": "1 Hacienda Way",
      |                            "addressLine3": "01055",
      |                            "town": "Madrid",
      |                            "countryCode": "ES"
      |                        },
      |                        "landRegistryTitleNumberKey": false,
      |                        "landRegistryTitleNumberValue": "Foreign property"
      |                    },
      |                    "heldPropertyTransaction": {
      |                        "methodOfHolding": "Acquisition",
      |                        "dateOfAcquisitionOrContribution": "2022-12-30",
      |                        "optPropertyAcquiredFromName": "Joe Sussex",
      |                        "optPropertyAcquiredFrom": {
      |                            "identityType": "individual",
      |                            "idNumber": "SX654321A"
      |                        },
      |                        "optConnectedPartyStatus": false,
      |                        "totalCostOfLandOrProperty": 14000000,
      |                        "optIndepValuationSupport": false,
      |                        "isLandOrPropertyResidential": false,
      |                        "optLeaseDetails": {
      |                            "lesseeName": "Leasee",
      |                            "leaseGrantDate": "2023-01-17",
      |                            "annualLeaseAmount": 500000,
      |                            "connectedPartyStatus": false
      |                        },
      |                        "landOrPropertyLeased": true,
      |                        "totalIncomeOrReceipts": 500000
      |                    },
      |                    "optDisposedPropertyTransaction": [
      |                        {
      |                            "methodOfDisposal": "Sold",
      |                            "optDateOfSale": "2022-11-09",
      |                            "optNameOfPurchaser": "Realty Purchasers Co.",
      |                            "optPropertyAcquiredFrom": {
      |                                "identityType": "ukCompany",
      |                                "idNumber": "JE463863"
      |                            },
      |                            "optSaleProceeds": 1550000,
      |                            "optConnectedPartyStatus": true,
      |                            "optIndepValuationSupport": false,
      |                            "portionStillHeld": true
      |                        },
      |                        {
      |                            "methodOfDisposal": "Sold",
      |                            "optDateOfSale": "2023-01-26",
      |                            "optNameOfPurchaser": "ABC Company Inc.",
      |                            "optPropertyAcquiredFrom": {
      |                                "identityType": "ukCompany",
      |                                "idNumber": "DA576257"
      |                            },
      |                            "optSaleProceeds": 10234.56,
      |                            "optConnectedPartyStatus": false,
      |                            "optIndepValuationSupport": false,
      |                            "portionStillHeld": true
      |                        }
      |                    ]
      |                }
      |            ]
      |        }
      |    }
      |}
      |""".stripMargin

  Feature("POST Requests for Standard PSR API") {

    Scenario("Verify 204 response when POST request with Land or Property") {
      When("Land Or Property is called the response should be 204")
      POSTRequestsHelper.postFullBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

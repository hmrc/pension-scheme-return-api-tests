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

class PensionSchemeReturn_POSTBondsOnlySpec extends BaseSpec {

  val pensionSchemeReturnPOSTServiceAPI: PensionSchemeReturnPOSTRequestService =
    new PensionSchemeReturnPOSTRequestService
  val authBearerToken: String                                                  = authHelper.getAuthBearerToken
  val payload: String                                                          =
    """
      |{
      |  "minimalRequiredSubmission": {
      |    "reportDetails": {
      |      "pstr": "00000042IN",
      |      "periodStart": "2023-04-06",
      |      "periodEnd": "2024-04-05"
      |    },
      |    "accountingPeriodDetails": {
      |      "recordVersion": "001",
      |      "accountingPeriods": [
      |        [
      |          "2023-04-06",
      |          "2024-04-05"
      |        ]
      |      ]
      |    },
      |    "schemeDesignatory": {
      |      "openBankAccount": true,
      |      "activeMembers": 23,
      |      "deferredMembers": 45,
      |      "pensionerMembers": 6,
      |      "totalPayments": 74
      |    }
      |  },
      |  "checkReturnDates": true,
      |  "assets": {
      |    "optBonds": {
      |      "optBondsWereAdded": true,
      |      "optBondsWereDisposed": false,
      |      "bondTransactions": [
      |        {
      |          "nameOfBonds": "Xenex Bonds",
      |          "methodOfHolding": "01",
      |          "optDateOfAcqOrContrib": "2022-10-06",
      |          "costOfBonds": 10234.56,
      |          "optConnectedPartyStatus": false,
      |          "bondsUnregulated": false,
      |          "optTotalIncomeOrReceipts": 50,
      |          "optBondsDisposed": [
      |            {
      |              "methodOfDisposal": "Sold",
      |              "optDateSold": "2022-11-30",
      |              "optAmountReceived": 12333.59,
      |              "optBondsPurchaserName": "Happy Bond Buyers Inc.",
      |              "optConnectedPartyStatus": false,
      |              "totalNowHeld": 120
      |            },
      |            {
      |              "methodOfDisposal": "Transferred",
      |              "totalNowHeld": 12
      |            },
      |            {
      |              "methodOfDisposal": "Other",
      |              "optOtherMethod": "OtherMethod",
      |              "totalNowHeld": 10
      |            }
      |          ]
      |        },
      |        {
      |          "nameOfBonds": "Really Goods Bonds ABC",
      |          "methodOfHolding": "02",
      |          "optDateOfAcqOrContrib": "2022-07-30",
      |          "costOfBonds": 2000.5,
      |          "optConnectedPartyStatus": false,
      |          "bondsUnregulated": false,
      |          "optTotalIncomeOrReceipts": 300
      |        }
      |      ]
      |    }
      |  }
      |}
      |""".stripMargin
  Feature("POST Request for Bonds Only Standard PSR API") {

    Scenario("Verify 204 response when POST request with Bonds Only") {
      When("Bonds only the response should be 204")
      POSTRequestsHelper.postFullBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

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

class PensionSchemeReturn_POSTOtherAssetsOnlySpec extends BaseSpec {

  val pensionSchemeReturnPOSTServiceAPI: PensionSchemeReturnPOSTRequestService =
    new PensionSchemeReturnPOSTRequestService
  val authBearerToken: String                                                  = authHelper.getAuthBearerToken
  val payload: String                                                          =
    """
      |{
      |  "minimalRequiredSubmission": {
      |    "reportDetails": {
      |      "pstr": "00000042IN",
      |      "periodStart": "2022-04-06",
      |      "periodEnd": "2023-04-05"
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
      |      "activeMembers": 1,
      |      "deferredMembers": 1,
      |      "pensionerMembers": 1
      |    }
      |  },
      |  "checkReturnDates": true,
      |  "assets": {
      |    "optOtherAssets": {
      |      "optOtherAssetsWereHeld": true,
      |      "optOtherAssetsWereDisposed": false,
      |      "otherAssetTransactions": [
      |        {
      |          "assetDescription": "Test asset",
      |          "methodOfHolding": "01",
      |          "optDateOfAcqOrContrib": "2023-03-05",
      |          "costOfAsset": 12.34,
      |          "optPropertyAcquiredFromName": "sdgfghdfh",
      |          "optPropertyAcquiredFrom": {
      |            "identityType": "other",
      |            "otherDescription": "gfhfghfghf"
      |          },
      |          "optConnectedStatus": false,
      |          "optIndepValuationSupport": false,
      |          "optMovableSchedule29A": false,
      |          "optTotalIncomeOrReceipts": 34.56,
      |          "optOtherAssetDisposed": [
      |            {
      |              "methodOfDisposal": "Sold",
      |              "optDateSold": "2022-11-30",
      |              "optPurchaserName": "Acme Express Ltd.",
      |              "optPropertyAcquiredFrom": {
      |                "identityType": "individual",
      |                "idNumber": "SX123456A"
      |              },
      |              "optTotalAmountReceived": 12333.59,
      |              "optConnectedStatus": false,
      |              "optSupportedByIndepValuation": false,
      |              "anyPartAssetStillHeld": true
      |            },
      |            {
      |              "methodOfDisposal": "Transferred",
      |              "anyPartAssetStillHeld": true
      |            },
      |            {
      |              "methodOfDisposal": "Other",
      |              "optOtherMethod": "OtherMethod",
      |              "anyPartAssetStillHeld": true
      |            }
      |          ]
      |        }
      |      ]
      |    }
      |  }
      |}
      |""".stripMargin
  Feature("POST Request for Other Assets Standard PSR API") {

    Scenario("Verify 204 response when POST request for Other Assets") {
      When("Other Assets POST response should be 204")
      POSTRequestsHelper.postFullBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

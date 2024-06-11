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

class PensionSchemeReturn_POSTMemberPaymentsOnlySpec extends BaseSpec {

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
      |  "membersPayments": {
      |    "employerContributionMade": false,
      |    "unallocatedContribsMade": false,
      |    "employerContributionsDetails": {
      |      "made": true,
      |      "completed": true
      |    },
      |    "transfersInCompleted": true,
      |    "transfersOutCompleted": true,
      |    "lumpSumReceived": true,
      |    "memberContributionMade": true,
      |    "pensionReceived": {
      |      "made": true,
      |      "completed": true
      |    },
      |    "benefitsSurrenderedDetails": {
      |      "made": true,
      |      "completed": true
      |    },
      |    "memberDetails": [
      |      {
      |        "personalDetails": {
      |          "firstName": "John",
      |          "lastName": "Doe",
      |          "dateOfBirth": "1990-10-10",
      |          "nino": "AB123456A"
      |        },
      |        "state": "Active",
      |        "employerContributions": [
      |          {
      |            "employerName": "Acme Ltd",
      |            "employerType": {
      |              "employerType": "UKCompany",
      |              "value": "11108499"
      |            },
      |            "totalTransferValue": 12.34
      |          },
      |          {
      |            "employerName": "Slack Ltd",
      |            "employerType": {
      |              "employerType": "UKPartnership",
      |              "value": "A1230849"
      |            },
      |            "totalTransferValue": 102.88
      |          }
      |        ],
      |        "transfersIn": [
      |          {
      |            "schemeName": "Test pension scheme",
      |            "dateOfTransfer": "2023-02-12",
      |            "transferValue": 12.34,
      |            "transferIncludedAsset": true,
      |            "transferSchemeType": {
      |              "key": "registeredPS",
      |              "value": "88390774ZZ"
      |            }
      |          }
      |        ],
      |        "transfersOut": [
      |          {
      |            "schemeName": "Test pension scheme out",
      |            "dateOfTransfer": "2023-02-12",
      |            "transferSchemeType": {
      |              "key": "registeredPS",
      |              "value": "76509173AA"
      |            }
      |          }
      |        ],
      |        "benefitsSurrendered": {
      |          "totalSurrendered": 12.34,
      |          "dateOfSurrender": "2022-12-12",
      |          "surrenderReason": "some reason"
      |        },
      |        "pensionAmountReceived": 12.34
      |      },
      |      {
      |        "personalDetails": {
      |          "firstName": "Jane",
      |          "lastName": "Dean",
      |          "dateOfBirth": "1995-06-01",
      |          "noNinoReason": "some reason"
      |        },
      |        "state": "Active",
      |        "employerContributions": [
      |          {
      |            "employerName": "Test Ltd",
      |            "employerType": {
      |              "employerType": "UKCompany",
      |              "value": "67308411"
      |            },
      |            "totalTransferValue": 23.35
      |          },
      |          {
      |            "employerName": "Legal Ltd",
      |            "employerType": {
      |              "employerType": "Other",
      |              "value": "some description"
      |            },
      |            "totalTransferValue": 553.01
      |          }
      |        ],
      |        "transfersIn": [
      |          {
      |            "schemeName": "overseas pension scheme",
      |            "dateOfTransfer": "2020-10-04",
      |            "transferValue": 45.67,
      |            "transferIncludedAsset": false,
      |            "transferSchemeType": {
      |              "key": "qualifyingRecognisedOverseasPS",
      |              "value": "Q654321"
      |            }
      |          },
      |          {
      |            "schemeName": "Test pension scheme",
      |            "dateOfTransfer": "2021-08-21",
      |            "transferValue": 67.89,
      |            "transferIncludedAsset": true,
      |            "transferSchemeType": {
      |              "key": "other",
      |              "value": "other value"
      |            }
      |          }
      |        ],
      |        "transfersOut": [
      |          {
      |            "schemeName": "overseas pension scheme out",
      |            "dateOfTransfer": "2020-10-04",
      |            "transferSchemeType": {
      |              "key": "qualifyingRecognisedOverseasPS",
      |              "value": "Q000002"
      |            }
      |          },
      |          {
      |            "schemeName": "Test pension scheme out",
      |            "dateOfTransfer": "2021-08-21",
      |            "transferSchemeType": {
      |              "key": "other",
      |              "value": "other value"
      |            }
      |          }
      |        ],
      |        "benefitsSurrendered": {
      |          "totalSurrendered": 12.34,
      |          "dateOfSurrender": "2022-12-12",
      |          "surrenderReason": "some reason"
      |        },
      |        "pensionAmountReceived": 12.34
      |      }
      |    ]
      |  }
      |}
      |""".stripMargin
  Feature("POST Request for Member Payments Standard PSR API") {

    Scenario("Verify 204 response when POST request with Member Payments only endpoint") {
      When("Member Payments POST response should be 204")
      POSTRequestsHelper.postFullBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

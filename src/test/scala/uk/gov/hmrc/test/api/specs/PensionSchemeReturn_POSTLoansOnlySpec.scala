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

class PensionSchemeReturn_POSTLoansOnlySpec extends BaseSpec {

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
      |  "loans": {
      |    "schemeHadLoans": true,
      |    "loanTransactions": [
      |      {
      |        "recipientIdentityType": {
      |          "identityType": "individual",
      |          "reasonNoIdNumber": "sdfsdf"
      |        },
      |        "loanRecipientName": "sdfsdfds",
      |        "connectedPartyStatus": true,
      |        "datePeriodLoanDetails": {
      |          "dateOfLoan": "2023-02-12",
      |          "loanTotalSchemeAssets": 3,
      |          "loanPeriodInMonths": 9
      |        },
      |        "loanAmountDetails": {
      |          "loanAmount": 9,
      |          "optCapRepaymentCY": 8,
      |          "optAmountOutstanding": 7
      |        },
      |        "equalInstallments": true,
      |        "loanInterestDetails": {
      |          "loanInterestAmount": 8,
      |          "loanInterestRate": 8,
      |          "optIntReceivedCY": 6
      |        },
      |        "optSecurityGivenDetails": "kjsdfvsd",
      |        "optOutstandingArrearsOnLoan": 273
      |      }
      |    ]
      |  }
      |}
      |""".stripMargin

  Feature("POST Request for Loans only Standard PSR API") {

    Scenario("Verify 204 response when POST request with Loans Only") {
      When("Loans Only is called the response should be 204")
      POSTRequestsHelper.postFullBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

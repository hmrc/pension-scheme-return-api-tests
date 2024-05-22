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


class PensionSchemeReturn_POSTNoBodyRequestSpec extends BaseSpec {

  val pensionSchemeReturnPOSTServiceAPI: PensionSchemeReturnPOSTRequestService = new PensionSchemeReturnPOSTRequestService
  val authBearerToken: String = authHelper.getAuthBearerToken
  val payload: String =
    """
      |
      |""".stripMargin

  Feature("POST Requests for Standard PSR API") {

    Scenario("Verify 400 response when POST request with no body") {
      When("the Not Found endpoint is called the response should be 400")
      POSTRequestsHelper.postNoBodyRequestEndpoint(authBearerToken, payload)
    }
  }
}

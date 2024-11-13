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

import uk.gov.hmrc.test.api.service.PensionSchemeReturnOverviewService

class PensionSchemeReturn_GETUnauthorizedOverviewSpec extends BaseSpec {

  val pensionSchemeReturnServiceAPI: PensionSchemeReturnOverviewService = new PensionSchemeReturnOverviewService
  val authBearerToken: String                                           = authHelper.getPSAPSPAuthBearerToken

  Feature("Overview Tests for PSA PSP Users") {

    Scenario("Get PSR Overview Unauthorised") {
      Given("The user is authenticated")
      When("PSA PSP user accessing the scheme as PSR and the response should be Unauthorized 401")
      overviewHelper.getPSRUnauthorizedOverviewEndpoint(authBearerToken, "24000001IN", "2024-04-05","2024-04-05")
    }

    Scenario("Get PSP Overview Authorised") {
      Given("The user is authenticated")
      When("PSA PSP user accessing the scheme as PSR and the response should be Authorized 200")
      overviewHelper.getPSPAuthorizedOverviewEndpoint(authBearerToken, "24000001IN", "2024-04-05", "2024-04-05")
    }
  }
}

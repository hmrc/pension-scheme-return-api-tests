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

class PensionSchemeReturnVersionsSpec extends BaseSpec {

  val pensionSchemeReturnServiceAPI: PensionSchemeReturnOverviewService = new PensionSchemeReturnOverviewService
  val authBearerToken: String                                           = authHelper.getAuthBearerToken

  Feature("Versions API") {

    Scenario("Get PSR Version details") {
      Given("The user is authenticated")

      When("the valid versions endpoint is called the response should be 200")
      versionHelper.listVersionsByPstrAndDate(authBearerToken, "24000001IN", "2022-04-06")
    }

    Scenario("Verify 200 response when calling Not Found for versions endpoint") {
      When("the Not Found endpoint is called the response should be 200")
      versionHelper.getNotFoundVersionsEndpoint(authBearerToken, "24000001IN_Not_found", "2021-04-06")
    }

    Scenario("Verify 403 Forbidden response when calling versions endpoint") {
      When("the endpoint is called so the response should be 403 Forbidden")
      versionHelper.getPSRVersionsForbiddenEndpoint(authBearerToken, "24000001IN", "2050-04-06")
    }

    Scenario("Verify 400 Bad Request response when calling Versions with Invalid date") {
      When("the endpoint is called with Invalid PSTR so the response should be 400")
      versionHelper.getPSRVersionBadRequestEndpoint(authBearerToken, "24000001IN_Invalid", "2021-04-06")
    }
  }
}

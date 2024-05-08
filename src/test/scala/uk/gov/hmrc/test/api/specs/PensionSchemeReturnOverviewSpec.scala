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

import uk.gov.hmrc.test.api.models.Overview
import uk.gov.hmrc.test.api.models.Overview.overviews
import uk.gov.hmrc.test.api.service.PensionSchemeReturnOverviewService

import java.time.LocalDate

class PensionSchemeReturnOverviewSpec extends BaseSpec {

  val pensionSchemeReturnServiceAPI: PensionSchemeReturnOverviewService = new PensionSchemeReturnOverviewService
  val authBearerToken: String = authHelper.getAuthBearerToken

  Feature("Overview API") {

    Scenario("Get an Overview details") {
      Given("The user is authenticated")

      When("the overview endpoint is called")
      val actualOverview: Seq[Overview] = overviewHelper.listOverviewsByPstrAndDate(authBearerToken, "24000001IN", LocalDate.of(2024, 4, 6), LocalDate.of(2021, 4, 5))
      Then("I am returned the overview details")
      actualOverview shouldBe overviews
    }

    Scenario("Verify 200 response when calling formBundle endpoint") {
      When("the form Bundle endpoint is called the response should be 200")
      overviewHelper.formBundleEndpoint(authBearerToken, "24000001IN", "000000021221" )
    }

    Scenario("Verify 422 response when calling unprocessed entity endpoint") {
      When("the endpoint is called with unprocessed entity so the response should be 422")
      overviewHelper.getPSREntityUnprocessedEndpoint(authBearerToken, "24000001IN", "456","9999-04-06")
    }

    Scenario("Verify 400 Bad Request response when calling Overview with Invalid date") {
      When("the endpoint is called with unprocessed entity so the response should be 400")
      overviewHelper.getPSROverviewBadRequestEndpoint(authBearerToken, "24000001IN_Invalid", "2021-04-06")
    }
  }
}

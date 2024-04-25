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

/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.api.specs

import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration
import scala.concurrent.{Await, Future}

class PSREndpointsSpec2 extends BaseSpec with HttpClient {
  Feature("Verify Overview page status code") {
    Scenario("Verify 200 response when calling PSTR endpoint") {
      When("The user is authenticated")
      val authToken = authHelper.getAuthBearerToken
      println(authToken)
    }
  }
  private def stubData(url: String, json: JsValue, bearerToken: String): Future[StandaloneWSRequest#Self#Response] =
    post(url, Json.stringify(json), ("Content-Type", "application/json"), ("Accept", "application/vnd.hmrc.P1.0+json"), ("Authorization", bearerToken))

}

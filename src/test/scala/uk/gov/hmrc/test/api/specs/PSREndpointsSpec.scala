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

import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.DurationInt

class PSREndpointsSpec extends BaseSpec with HttpClient {

  val authToken = "Bearer BXQ3/Treo4kQCZvVcCqKPr6c+dfo3p6JMDENSlBC33fWRpeIiml9cOyffVa5QQtx/yEQIskQAp561mynILGgYpx0tq2a+GgFkqZH4WoD/MNyWI7NuySUp8uWRYns8qwzYTmzA4eFGBXFDsseMRxkxHXa1NlJwLHxyrmTLi1XdP39KwIkeIPK/mMlBESjue4V"

  Feature("Verify Overview page status code2") {

    Scenario("Verify 200 response when calling overview endpoint") {

      When("The user is authenticated")
     // val authToken = authHelper.getAuthBearerToken

    //  val authToken = "Bearer BXQ3/Treo4kQCZvVcCqKPrWp0CFFc/ifW5b9jd8HcXBy2Ot4oUUeREnmMQmdvnSnC0qXqPGMgOuUgAd9IRGytSp1tsn4ccjV9YBUJWcNORJkskRHCN0eRSTXIGrNXEOtb5CWd8f0h1K8HUSy3E6XWyvQ1YCg7FM6YUan4wbX+Lr9KwIkeIPK/mMlBESjue4V"

      When("the overview endpoint is called")

      val serviceUrl   = TestConfiguration.url("pension-scheme-return-fe")
      val overviewEndpoint = s"$serviceUrl/S2400000001/overview"

      val result = Await.result(get(overviewEndpoint,"Authorization" -> authToken), 10.seconds)

      Then("the response code is 200")

      result.status shouldBe 200
    }

    Scenario("Verify 200 response when calling PSTR endpoint") {

      When("the PSTR endpoint is called")

      val serviceUrl = TestConfiguration.url("pension-scheme-return")
      println(serviceUrl)
      val overviewEndpoint = s"$serviceUrl/psr/overview/24000001IN?fromDate=2018-04-06&toDate=2024-04-06"

      val result = Await.result(get(overviewEndpoint, "Authorization" -> authToken), 10.seconds)
      println(result.statusText).prettifier
      Then("the response code is 200")
      result.status shouldBe 200

    }
  }

  private def stubData(url: String, json: JsValue, bearerToken: String): Future[StandaloneWSRequest#Self#Response] =
    post(url, Json.stringify(json), ("Content-Type", "application/json"), ("Accept", "application/vnd.hmrc.P1.0+json"), ("Authorization", bearerToken))

}

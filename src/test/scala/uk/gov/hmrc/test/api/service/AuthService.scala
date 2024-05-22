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

package uk.gov.hmrc.test.api.service

import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration

import scala.concurrent.Await
import scala.concurrent.duration._

class AuthService extends HttpClient {

  val host: String = TestConfiguration.url("auth")

  val authPayloadPSR: String =
    s"""{
       |  "credId": "1234",
       |  "credentialStrength": "strong",
       |  "confidenceLevel": 50,
       |  "credentialRole": "User",
       |  "affinityGroup": "Organisation",
       |  "email": "user@test.com",
       |  "excludeGnapToken": true,
       |  "enrolments": [
       |    {
       |      "key": "HMRC-PODS-ORG",
       |      "identifiers": [
       |        {
       |          "key": "PsaID",
       |          "value": "A2100005"
       |        }
       |      ],
       |      "state": "Activated"
       |    }
       |  ]
       |}""".stripMargin

  def postLogin: StandaloneWSRequest#Self#Response = {
    val url = s"$host/government-gateway/session/login"
    Await.result(
      post(url, authPayloadPSR, ("Content-Type", "application/json")),
      10.seconds
    )
  }

}

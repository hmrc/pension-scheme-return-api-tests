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

import play.api.libs.json.Json
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration

import javax.inject.Inject
import scala.concurrent.Await
import scala.concurrent.duration._


class AuthService_2 @Inject()() extends HttpClient {

  val host: String = TestConfiguration.url("auth-login-api")

  val bearerPayload: String =
    """
      |{
      |//       |  "authorityId": "1234",
      |//       |  "redirectionUrl": "http://localhost:9949/auth-login-stub/session",
      |//       |  "CredentialsStrength":"strong",
      |//       |  "ConfidenceLevel": "50",
      |//       |  "affinityGroup": "Organisation",
      |//       |  "email": "user@test.com",
      |//       |  "CredentialRole": "User",
      |//       |  "additionalInfo.emailVerified": "N/A",
      |//       |  "presets-dropdown": "IR-SA",
      |//       |  "enrolment[0].name": "HMRC-PODS-ORG",
      |//       |  "enrolment[0].taxIdentifier[0].name": "PsaID",
      |//       |  "enrolment[0].taxIdentifier[0].value": "A2100005",
      |//       |  "enrolment[0].state": "Activated",
      |//       |  "excludeGnapToken": "true",
      |//       |   "ttl": 5000
      |//       |}
      |""".stripMargin

  def getBearerToken: StandaloneWSRequest#Self#Response =
    Await.result(
      postWithJson(
        s"$host/application/session/login",
        Json.parse(bearerPayload)
      ),
      10.seconds
    )
}

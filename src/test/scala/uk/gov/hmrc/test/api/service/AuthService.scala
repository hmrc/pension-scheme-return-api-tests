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

class AuthService extends HttpClient {

  val host: String = TestConfiguration.url("login-page")
  val authPayload: String =
    s"""
       |{
       |  "authorityId": "1234",
       |  "redirectionUrl": "http://localhost:9949/auth-login-stub/session",
       |  "CredentialsStrength":"strong",
       |  "ConfidenceLevel": "50",
       |  "affinityGroup": "Organisation",
       |  "email": "user@test.com",
       |  "CredentialRole": "User",
       |  "additionalInfo.emailVerified": "N/A",
       |  "presets-dropdown": "IR-SA",
       |  "enrolment[0].name": "HMRC-PODS-ORG",
       |  "enrolment[0].taxIdentifier[0].name": "PsaID",
       |  "enrolment[0].taxIdentifier[0].value": "A2100005",
       |  "enrolment[0].state": "Activated",
       |  "excludeGnapToken": "true",
       |   "ttl": 5000
       |}
     """.stripMargin

  def postLogin: StandaloneWSRequest#Self#Response = {
    // val url = s"$host/pension-scheme-return/S0000000042/overview"
    //val url = s"http://localhost:9949/auth-login-stub/gg-sign-in"
    // val url = s"$host/application/session/login"
    Await.result(
      // post(url, authPayload, ("Content-Type", "application/json")),
      post("http://localhost:9949/auth-login-stub/gg-sign-in", authPayload, ("Content-Type", "application/json")),
      10.seconds
    )
  }
}





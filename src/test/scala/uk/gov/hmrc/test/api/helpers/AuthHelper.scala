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

package uk.gov.hmrc.test.api.helpers

import org.scalatest.Assertions.fail
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.service.{AuthService, AuthServicePSAPSP}

class AuthHelper {

  val authAPI: AuthService             = new AuthService
  val authAPIPSAPSP: AuthServicePSAPSP = new AuthServicePSAPSP

  def getAuthBearerToken: String = {
    val authServiceRequestResponse: StandaloneWSRequest#Self#Response = authAPI.postLogin
    authServiceRequestResponse.header("Authorization").getOrElse(fail("Could not obtain auth bearer token"))
  }

  def getPSAPSPAuthBearerToken: String = {
    val authServiceRequestResponse: StandaloneWSRequest#Self#Response = authAPIPSAPSP.postLogin
    authServiceRequestResponse.header("Authorization").getOrElse(fail("Could not obtain auth bearer token"))
  }
}

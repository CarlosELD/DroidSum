package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class LoginResponse {
    @Element(name = "Body")
    var body: Body? = null

    class Body {
        @Element(name = "accesoLoginResponse", required = false)
        var accessLoginResponse: AccessLoginResponse? = null
    }

    class AccessLoginResponse {
        @Element(name = "accesoLoginResult", required = false)
        var result: String? = null
    }
}


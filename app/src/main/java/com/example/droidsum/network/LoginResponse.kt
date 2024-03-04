package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class LoginResponse {
    @field:Element(name = "Body")
    var body: Body? = null

    class Body {
        @field:Element(name = "accesoLoginResponse", required = false)
        var accesoLoginResponse: AccesoLoginResponse? = null
    }

    class AccesoLoginResponse {
        @field:Element(name = "accesoLoginResult", required = false)
        var accesoLoginResult: String? = null
    }
}


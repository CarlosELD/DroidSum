package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class AccessProfile {
    @Element(name = "Body")
    var body: Body? = null

    class Body {
        @Element(name = "getPerfilResponse", required = false)
        var getPerfilResponse: GetPerfilResponse? = null
    }

    class GetPerfilResponse {
        @Element(name = "getPerfilResult", required = false)
        var result: String? = null
    }
}
package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class LoginRequest {
    @field:Element(name = "Body")
    var body: Body? = null

    class Body {
        @field:Element(name = "accesoLogin", required = false)
        var accesoLogin: AccesoLogin? = null
    }

    class AccesoLogin {
        @field:Element(name = "strMatricula", required = false)
        var strMatricula: String? = null

        @field:Element(name = "strContrasenia", required = false)
        var strContrasenia: String? = null

        @field:Element(name = "tipoUsuario", required = false)
        var tipoUsuario: String? = null
    }
}


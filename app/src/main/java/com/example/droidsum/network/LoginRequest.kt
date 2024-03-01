package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class LoginRequest {
    @Element(name = "Body")
    var body: Body? = null

    class Body {
        @Element(name = "accesoLogin", required = false)
        var accessLogin: AccessLogin? = null
    }

    class AccessLogin {
        @Element(name = "strMatricula", required = false)
        var matricula: String? = null

        @Element(name = "strContrasenia", required = false)
        var contrasenia: String? = null

        @Element(name = "tipoUsuario", required = false)
        var tipoUsuario: String? = null
    }
}


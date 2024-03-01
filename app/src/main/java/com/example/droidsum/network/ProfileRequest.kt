package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class ProfileRequest {
    @Element(name = "Body")
    var body: Body? = null

    class Body {
        @Element(name = "getAlumnoAcademicoWithLineamiento", required = false)
        var getAlumnoAcademicoWithLineamiento: GetAlumnoAcademicoWithLineamiento? = null
    }

    class GetAlumnoAcademicoWithLineamiento { // Puedes agregar aquí cualquier parámetro necesario para la solicitud de perfil
    }
}
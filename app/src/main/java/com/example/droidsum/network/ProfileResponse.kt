package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class ProfileResponse {
    @Element(name = "Body")
    var body: Body? = null

    class Body {
        @Element(name = "getAlumnoAcademicoWithLineamientoResponse", required = false)
        var getAlumnoAcademicoWithLineamientoResponse: GetAlumnoAcademicoWithLineamientoResponse? =
            null
    }

    class GetAlumnoAcademicoWithLineamientoResponse {
        @Element(name = "getAlumnoAcademicoWithLineamientoResult", required = false)
        var result: String? = null
    }
}
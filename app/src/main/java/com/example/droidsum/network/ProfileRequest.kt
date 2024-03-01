package com.example.droidsum.network

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
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

    class GetAlumnoAcademicoWithLineamiento {
        @Element(name = "matricula", required = false)
        var matricula: String? = null

        @Element(name = "contrasenia", required = false)
        var contrasenia: String? = null

        @Element(name = "tipoUsuario", required = false)
        var tipoUsuario: String? = null

        @Element(name = "nombreCompleto", required = false)
        var nombreCompleto: String? = null

        @Element(name = "semestre", required = false)
        var semestre: String? = null

        @Element(name = "carrera", required = false)
        var carrera: String? = null

        @Element(name = "numeroControl", required = false)
        var numeroControl: String? = null

        @ElementList(name = "calificaciones", required = false)
        var calificaciones: List<Calificacion>? = null

        class Calificacion {
            @Element(name = "materia", required = false)
            var materia: String? = null

            @Element(name = "calificacion", required = false)
            var calificacion: String? = null
        }
    }
}
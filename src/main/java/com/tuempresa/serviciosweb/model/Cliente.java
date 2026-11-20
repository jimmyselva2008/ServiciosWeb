package com.tuempresa.serviciosweb.model;

import java.math.BigDecimal;

public class Cliente {

    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String fechaNacimiento;
    private String pais;
    private String ciudad;
    private String direccion;
    private BigDecimal ingresoAnual;
    private Integer puntajeCrediticio;
    private Boolean tieneDeudaImpaga;
    private BigDecimal montoDeudaImpaga;
    private String estado;
    private String fechaCreacion;
    private String fechaActualizacion;

    // Constructor lleno
    public Cliente(long id, String nombre, String apellido, String correo,
                   String telefono, String fechaNacimiento, String pais,
                   String ciudad, String direccion, BigDecimal ingresoAnual,
                   Integer puntajeCrediticio, Boolean tieneDeudaImpaga,
                   BigDecimal montoDeudaImpaga, String estado,
                   String fechaCreacion, String fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.ingresoAnual = ingresoAnual;
        this.puntajeCrediticio = puntajeCrediticio;
        this.tieneDeudaImpaga = tieneDeudaImpaga;
        this.montoDeudaImpaga = montoDeudaImpaga;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getIngresoAnual() {
        return ingresoAnual;
    }

    public void setIngresoAnual(BigDecimal ingresoAnual) {
        this.ingresoAnual = ingresoAnual;
    }

    public Integer getPuntajeCrediticio() {
        return puntajeCrediticio;
    }

    public void setPuntajeCrediticio(Integer puntajeCrediticio) {
        this.puntajeCrediticio = puntajeCrediticio;
    }

    public Boolean getTieneDeudaImpaga() {
        return tieneDeudaImpaga;
    }

    public void setTieneDeudaImpaga(Boolean tieneDeudaImpaga) {
        this.tieneDeudaImpaga = tieneDeudaImpaga;
    }

    public BigDecimal getMontoDeudaImpaga() {
        return montoDeudaImpaga;
    }

    public void setMontoDeudaImpaga(BigDecimal montoDeudaImpaga) {
        this.montoDeudaImpaga = montoDeudaImpaga;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
// Mostrar campos dinámicos en el registro
function mostrarCampos() {
    const rol = document.getElementById('selectRol').value;
    document.getElementById('campos-alumno').classList.remove('visible');
    document.getElementById('campos-profesor').classList.remove('visible');
    document.getElementById('campos-empresa').classList.remove('visible');
    if (rol) {
        document.getElementById('campos-' + rol).classList.add('visible');
    }
}

// Filtrar tabla de usuarios en el panel admin
function filtrarTabla() {
    const busqueda = document.getElementById('buscadorUsuarios').value.toLowerCase();
    const rol = document.getElementById('filtroRol').value.toLowerCase();
    const filas = document.querySelectorAll('#tablaUsuarios tbody tr');

    filas.forEach(fila => {
        const texto = fila.textContent.toLowerCase();
        const coincideBusqueda = texto.includes(busqueda);
        const coincideRol = rol === '' || texto.includes(rol);
        fila.style.display = coincideBusqueda && coincideRol ? '' : 'none';
    });
}

// Filtrar tabla de alumnos
function filtrarAlumnos() {
    const busqueda = document.getElementById('buscadorAlumnos').value.toLowerCase();
    const filas = document.querySelectorAll('#tablaAlumnos tbody tr');
    filas.forEach(fila => {
        const texto = fila.textContent.toLowerCase();
        fila.style.display = texto.includes(busqueda) ? '' : 'none';
    });
}

// Filtrar convenios
function filtrarConvenios() {
    const busqueda = document.getElementById('buscadorConvenios').value.toLowerCase();
    const estado = document.getElementById('filtroEstado').value.toLowerCase();
    const filas = document.querySelectorAll('#tablaConvenios tbody tr');
    filas.forEach(fila => {
        const texto = fila.textContent.toLowerCase();
        const coincideBusqueda = texto.includes(busqueda);
        const coincideEstado = estado === '' || texto.includes(estado);
        fila.style.display = coincideBusqueda && coincideEstado ? '' : 'none';
    });
}
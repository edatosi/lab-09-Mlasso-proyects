package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 *    Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test). 
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 *    java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        return cantidad;
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        // TODO Etapa 2: cree un Nodo, enlácelo a la cabeza actual
        //               y actualice la cabeza y la cantidad.
        // 1. crear el nodo
            Nodo<T> nuevoNodo = new Nodo<T>(dato);
        // 2. conectar nuevo nodo a la cabeza
            nuevoNodo.setSiguiente(this.cabeza);
        // 3. atualizar la cabeza
            this.cabeza = nuevoNodo;
        // 4. aumentar la cantidad
            cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        // TODO Etapa 2: si la lista está vacía es igual que agregarAlInicio;
        //               si no, recorra hasta el último nodo y enlace el nuevo.
            if (estaVacia()) {
                agregarAlInicio(dato);
        } else  {
                // 1. verificar el ultimo nodo
                Nodo<T> puntero = this.cabeza;
                while (puntero.getSiguiente() != null) {
                    puntero = puntero.getSiguiente();
                }
                // 2. crear el nodo
                Nodo<T> nuevoNodo = new Nodo<T>(dato);
            //   3. conectar el ultimo nodo con el nuevo
            puntero.setSiguiente(nuevoNodo);
            // 4. atualizar el nodo final
            nuevoNodo.setSiguiente(null);
            // 5. aumentar la cantidad
            cantidad++;
        }


    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {
            if (estaVacia()) {
                throw new NoSuchElementException("la lista está vacía");
        } else  {
                return this.cabeza.getDato();
        }
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public   T obtenerUltimo() {
            if (estaVacia()) {
                throw new NoSuchElementException("la lista está vacía");
        } else  {
                // 1. verificar el ultimo nodo
                Nodo<T> puntero = this.cabeza;
                while (puntero.getSiguiente() != null) {
                    puntero = puntero.getSiguiente();
                }
                // 2. retornar datos del ultimo nodo
                return puntero.getDato();
            }
    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {
        // TODO Etapa 3: valide el índice y recorra la lista 'indice' veces.
        // 1. validar
            if (indice <0 || indice >= tamanio()) {
                throw new IndexOutOfBoundsException("Indice incorrecto");
            } else  {
                // buscar el nodo avanzando entre nodos desde la cabeza       
                Nodo<T> puntero = this.cabeza;
                for (int i = 0; i < indice; i++) {
                    puntero = puntero.getSiguiente();
                }
                // 3. retornar datos del ultimo nodo
                return puntero.getDato();
            }
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        // TODO Etapa 4: use indiceDe o recorra la lista.
        //               Cuidado: para comparar use Objects.equals(a, b).
        return indiceDe(dato) != -1;
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        // TODO Etapa 4: escriba su código aquí
        Nodo<T> puntero = this.cabeza;
        int indice = 0;
        // 1. iniciar bucle para buscar el indice del dato
        while (puntero != null) {
            // 2. comparar el dato del nodo con el buscado
                if (Objects.equals(puntero.getDato(), dato)) {
                    return indice;
                }
                puntero = puntero.getSiguiente();
                indice++;
        }
            // retorno si no se encuentra
            return -1;
    }       

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {
        // TODO Etapa 5: casos: indice 0 (inicio), o detenerse en el nodo
        //               ANTERIOR a la posición y enlazar allí el nuevo nodo.
        // 1. validar (aquí SÍ se permite indice == tamanio())
        if (indice < 0 || indice > tamanio()) {
            throw new IndexOutOfBoundsException("Indice incorrecto");
        }
        // 2. caso especial: insertar al inicio
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        // 3. llegar al nodo ANTERIOR a la posición
        Nodo<T> anterior = this.cabeza;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }
        // 4. conectar primero con el siguiente y luego con el anterior
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevoNodo);
        // 5. aumentar la cantidad
        cantidad++;

        
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        // TODO Etapa 5: escriba su código aquí
        // 1. validar (aquí el elemento debe existir: indice < tamanio())
        if (indice < 0 || indice >= tamanio()) {
            throw new IndexOutOfBoundsException("Indice incorrecto");
        }
        // 2. buscar el nodo de la posición
        Nodo<T> puntero = this.cabeza;
        for (int i = 0; i < indice; i++) {
            puntero = puntero.getSiguiente();
        }
        // 3. guardar el dato viejo ANTES de cambiarlo
        T datoAnterior = puntero.getDato();
        // 4. poner el dato nuevo
        puntero.setDato(dato);
        // 5. retornar el dato que había antes
        return datoAnterior;
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        // TODO Etapa 6: mueva la cabeza al siguiente nodo.
            if (estaVacia()) {
                throw new NoSuchElementException("la lista está vacía");
        } else  {
                T datoEliminado = this.cabeza.getDato();
                this.cabeza = this.cabeza.getSiguiente();
                cantidad--;
                return datoEliminado;
        }
    }       

    /**
     * Elimina y retorna el último elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        // TODO Etapa 6: hay que detenerse en el PENULTIMO nodo.
        //               Atención al caso de un solo elemento.
        if (estaVacia()) {
            throw new NoSuchElementException("La lista esta vacia");
        } else  {
            // el ultimo dato esta en el indice anterior a la cantidad
            return eliminar(cantidad - 1);
        }
    }

    /**
       * Elimina y retorna el elemento de la posición indicada.
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {
        // TODO Etapa 6: escriba su código aquí
         // 1. validar que el elemento exista
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Indice incorrecto");
        }
        // 2. caso especial: el primero no tiene nodo anterior
        if (indice == 0) {
            return eliminarPrimero();
        }
        // 3. llegar al nodo ANTERIOR al que se va a eliminar
        Nodo<T> anterior = this.cabeza;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }
        // 4. guardar el nodo que se va a quitar
        Nodo<T> nodoAEliminar = anterior.getSiguiente();
        //   5. saltarlo: el anterior apunta al siguiente del eliminado
        anterior.setSiguiente(nodoAEliminar.getSiguiente());
        // 6. actualizar la cantidad y retornar el dato eliminado
        cantidad--;
        return nodoAEliminar.getDato();
    }

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {
        // TODO Etapa 6: puede apoyarse en indiceDe y eliminar(int).
        // 1. buscar la posición de la primera aparición
            int indice = indiceDe(dato);
        // 2. si no está, no hay nada que eliminar
        if (indice == -1) {
            return false;
        }
        // 3. eliminar por posición y confirmar
        eliminar(indice);
        return true;
    }

    /*  * Deja la lista vacía. */
    public void vaciar() {
        // TODO Etapa 6: escriba su código aquí
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {
        // TODO Etapa 7: recorra la lista con un StringBuilder.
        // 1. abrir la lista
        StringBuilder texto = new StringBuilder("[");
        // 2. recorrer todos los nodos
        Nodo<T> puntero = this.cabeza;
        while (puntero != null) {
            // 3. agregar el dato del nodo actual
            texto.append(puntero.getDato());
            // 4. poner la coma solo si queda otro nodo después
                if (puntero.getSiguiente() != null) {
                    texto.append(", ");
                }
                puntero = puntero.getSiguiente();
        }
            // 5. cerrar la lista
            texto.append("]");
            return texto.toString();
    }       

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {
        // TODO Etapa 8: use tres referencias: anterior, actual, siguiente.
        Nodo<T> anterior = null;
        Nodo<T> actual = this.cabeza;
        // 1. recorrer todos los nodos
        while (actual != null) {
            // 2. guardar el siguiente ANTES de romper el enlace
            Nodo<T> siguiente = actual.getSiguiente();
            // 3. dar vuelta a la flecha: ahora apunta al nodo anterior
            actual.setSiguiente(anterior);
            // 4. avanzar los dos punteros
            anterior = actual;
            actual = siguiente;
        }
        // 5. el nuevo primer nodo es el último que se procesó
        this.cabeza = anterior;
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        // TODO Etapa 8: escriba su código aquí
        // 1. validar lista vacía
        if (estaVacia()) {
            throw new NoSuchElementException("la lista está vacía");
        }
        // 2. los dos punteros empiezan en la cabeza
        Nodo<T> lento = this.cabeza;
        Nodo<T> rapido = this.cabeza;
        // 3. mientras el rápido pueda dar sus 2 pasos
        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        // 4. el lento quedó en el medio
        return lento.getDato();
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        // TODO Etapa 8: escriba su código aquí
            Nodo<T> actual = this.cabeza;
        // 1. cada nodo es el "original" cuyos repetidos se van a quitar
        while (actual != null) {
            // 2. revisar los nodos que vienen DESPUÉS del actual
            Nodo<T> previo = actual;
            while (previo.getSiguiente() != null) {
                Nodo<T> candidato = previo.getSiguiente();
                if (Objects.equals(candidato.getDato(), actual.getDato())) {
                    // 3. es repetido: saltarlo y descontarlo
                    previo.setSiguiente(candidato.getSiguiente());
                    cantidad--;
                } else {
                    // 4. no es repetido: avanzar
                    previo = candidato;
                }
            }
            // 5. pasar al siguiente original
            actual = actual.getSiguiente();
        }
    }
}

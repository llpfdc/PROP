package prop;

import prop.domain.CtrlDominio;
import prop.persistence.CtrlPersistencia;
import prop.presentacion.*;

import java.util.Scanner;

/**
 * @author Alejandro Durán Saborido
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Boolean terminal = false;
        if (!terminal) {
            javax.swing.SwingUtilities.invokeLater (
                    new Runnable() {
                        public void run() {
                            CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                            ctrlPresentacion.inicializarBienvenida();
                        }});
        }
        else {
            CtrlDominio CD = new CtrlDominio();

            CtrlPersistencia CP = new CtrlPersistencia(CD);
            System.out.println("HOJA DE CÁLCULO\n\n");
            Scanner capt = new Scanner(System.in);

            System.out.println("Creando nuevo documento...");

            System.out.print("Ingrese nombre del documento: ");
            String s = capt.nextLine();
            if (s.equals("")) s = "Nuevo documento";

            System.out.print("Ingrese número de filas de la primera hoja: ");
            int filas = capt.nextInt();

            System.out.print("Ingrese número de columnas de la primera hoja: ");
            int columnas = capt.nextInt();
            System.out.print("\n");
            

            while (!CD.crear_documento(s, filas, columnas)) {
                System.out.println("Error: El número de filas y el número de columnas debe ser mayor que 0.\n");

                System.out.print("Ingrese número de filas de la primera hoja: ");
                filas = capt.nextInt();

                System.out.print("Ingrese número de columnas de la primera hoja: ");
                columnas = capt.nextInt();
                System.out.print("\n");
            }

            boolean cerrar = false;

            while (!cerrar) {
                System.out.println("1: Nuevo documento");
                System.out.println("2: Añadir hoja");
                System.out.println("3: Eliminar hoja");
                System.out.println("4: Ver hojas disposibles");
                System.out.println("5: Renombrar hoja");
                System.out.println("6: Seleccionar hoja");
                System.out.println("7: Importar CSV");
                System.out.println("0: Cerrar");
                System.out.print("Seleccione qué desea hacer (Ingrese solo el número del comando): ");
                int comando = capt.nextInt();
                System.out.print("\n");

                switch (comando) {
                    case 1: {
                        System.out.print("Nombre del Documento: ");
                        capt.nextLine();
                        s = capt.nextLine();

                        System.out.print("Ingrese número de filas: ");
                        filas = capt.nextInt();

                        System.out.print("Ingrese número de columnas: ");
                        columnas = capt.nextInt();
                        System.out.print("\n");

                        while (!CD.crear_documento(s, filas, columnas)) {
                            System.out.println("Error: El número de filas y el número de columnas debe ser mayor que 0.");

                            System.out.print("Ingrese número de filas de la primera hoja: ");
                            filas = capt.nextInt();

                            System.out.print("Ingrese número de columnas de la primera hoja: ");
                            columnas = capt.nextInt();
                            System.out.print("\n");
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("Ingrese número de filas: ");
                        filas = capt.nextInt();

                        System.out.print("Ingrese número de columnas: ");
                        columnas = capt.nextInt();

                        System.out.print("\n");

                        if (!CD.add_hoja(filas, columnas)) {
                            System.out.println("Error: El número de filas y el número de columnas debe ser mayor que 0.\n");
                        }
                        System.out.println("Número de hojas actual: " + CD.get_doc_size());
                        break;
                    }
                    case 3: {
                        System.out.print("Ingrese número de la hoja a eliminar: ");
                        int num_hoja = capt.nextInt();
                        System.out.print("\n");

                        if (CD.get_doc_size() == 1)
                            System.out.println("Error: El documento debe tener al menos una hoja en todo momento.\n");

                        else {
                            if (!CD.remove_hoja(num_hoja - 1)) {
                                System.out.println("Error: La hoja " + num_hoja + " no existe.\n");
                            } else System.out.println("La hoja " + num_hoja + " ha sido eliminada correctamente.\n");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println(CD.view_hojas() + "\n");
                        break;
                    }
                    case 5: {
                        System.out.print("Ingrese el número de la hoja a renombrar: ");
                        int num_hoja = capt.nextInt();

                        System.out.print("Ingrese nuevo nombre: ");
                        capt.nextLine();
                        String new_name = capt.nextLine();

                        if (!CD.rename_hoja(num_hoja - 1, new_name)) {
                            System.out.println("Error: La hoja " + num_hoja + " no existe.\n");
                        } else
                            System.out.println("El nuevo nombre de la hoja es: " + CD.get_nombre_hoja(num_hoja - 1) + "\n");
                        break;
                    }
                    case 6: {
                        boolean en_hoja = false;
                        System.out.println(CD.view_hojas() + "\n");
                        System.out.print("¿Con qué hoja desea trabajar? (Ingrese solo el número): ");
                        int num_hoja = capt.nextInt();
                        if (!CD.select_hoja(num_hoja - 1)) {
                            System.out.println("\nError: La hoja " + num_hoja + " no existe.\n");
                        } else {
                            System.out.println("\nTrabajando con la hoja: " + num_hoja + "\n");
                            en_hoja = true;
                        }


                        while (en_hoja) {
                            System.out.println("1: Añadir fila");
                            System.out.println("2: Añadir columna");
                            System.out.println("3: Eliminar fila");
                            System.out.println("4: Eliminar columna");
                            System.out.println("5: Buscar contenido");
                            System.out.println("6: Copiar Celda");
                            System.out.println("7: Pegar Celda");
                            System.out.println("8: Modificar contenido de celda");
                            System.out.println("9: Seleccionar bloque");
                            System.out.println("10: Imprimir hoja");
                            System.out.println("0: Volver a documento");
                            System.out.print("Seleccione qué desea hacer (Ingrese solo el número del comando): ");
                            int comando2 = capt.nextInt();
                            System.out.print("\n");

                            switch (comando2) {
                                case 1: {
                                    System.out.println("1: Al final");
                                    System.out.println("2: Intercalada");
                                    System.out.print("¿Desea añadir la fila al final o intercalada?: ");
                                    int i = capt.nextInt();
                                    System.out.print("\n");

                                    if (i == 1) {
                                        CD.add_fila();
                                    } else if (i == 2) {
                                        System.out.print("Ingrese la posición donde desea añadirla (de 1 al número de filas): ");
                                        int id = capt.nextInt();
                                        System.out.print("\n");

                                        if (!CD.add_fila(id - 1)) {
                                            System.out.println("Error: La posición debe estar dentro del rango de la hoja.\n");
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("1: Al final");
                                    System.out.println("2: Intercalada");
                                    System.out.print("¿Desea añadir la columna al final o intercalada? (Ingrese solo el número): ");
                                    int i = capt.nextInt();
                                    System.out.print("\n");

                                    if (i == 1) {
                                        CD.add_columna();
                                    } else if (i == 2) {
                                        System.out.print("Ingrese la posición donde desea añadirla (de 1 al número de columnas): ");
                                        int id = capt.nextInt();
                                        System.out.print("\n");

                                        if (!CD.add_columna(id - 1))
                                            System.out.println("Error: La posición debe estar dentro del rango de la hoja.\n");
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.print("Ingrese la posición de la fila que desea eliminar: ");
                                    int num_fila = capt.nextInt();
                                    System.out.print("\n");

                                    if (!CD.remove_fila(num_fila - 1))
                                        System.out.println("Error: La posición debe estar dentro del rango de la hoja.\n");
                                    break;
                                }
                                case 4: {
                                    System.out.print("Ingrese la posición de la columna que desea eliminar: ");
                                    int num_columna = capt.nextInt();
                                    System.out.print("\n");

                                    if (!CD.remove_columna(num_columna - 1))
                                        System.out.println("Error: La posición debe estar dentro del rango de la hoja.\n");
                                    break;
                                }
                                case 5: {
                                    System.out.println("1: Solo buscar");
                                    System.out.println("2: Buscar y reemplazar");
                                    System.out.print("¿Desea reeemplazar también? (Ingrese solo el número): ");
                                    int i = capt.nextInt();
                                    System.out.print("\n");

                                    if (i == 1) {
                                        System.out.println("1: Coincidir mayúsculas y minúsculas");
                                        System.out.println("2: Coincidir con el contenido de toda la celda");
                                        System.out.println("3: Coincidir con el contenido de toda la celda y mayúsculas y minúsculas");
                                        System.out.println("4: Normal");
                                        System.out.print("¿Qué tipo de búsqueda desea realizar? (Ingrese solo el número): ");
                                        int j = capt.nextInt();
                                        System.out.print("\n");

                                        System.out.print("Ingrese qué desea buscar: ");
                                        capt.nextLine();
                                        String search = capt.nextLine();
                                        System.out.print("\n");

                                        switch (j) {
                                            case 1: {
                                                CD.hoja_buscar_contenido_en_substring_con_mayus_minus(search);
                                                break;
                                            }
                                            case 2: {
                                                CD.hoja_buscar_contenido_exacto_sin_mayus_minus(search);
                                                break;
                                            }
                                            case 3: {
                                                CD.hoja_buscar_contenido_exacto_con_mayus_minus(search);
                                                break;
                                            }
                                            case 4: {
                                                CD.hoja_buscar_contenido_en_substring_sin_mayus_minus(search);
                                                break;
                                            }
                                        }
                                    } else if (i == 2) {
                                        System.out.println("1: Coincidir mayúsculas y minúsculas");
                                        System.out.println("2: Coincidir con el contenido de toda la celda");
                                        System.out.println("3: Coincidir con el contenido de toda la celda y mayúsculas y minúsculas");
                                        System.out.println("4: Normal");
                                        System.out.print("¿Qué tipo de búsqueda y reemplazo desea realizar? (Ingrese solo el número): ");
                                        int j = capt.nextInt();
                                        System.out.print("\n");

                                        System.out.print("Ingrese qué desea buscar: ");
                                        capt.nextLine();
                                        String search = capt.nextLine();

                                        System.out.print("Ingrese con qué desea reemplazar: ");
                                        String replace = capt.nextLine();
                                        System.out.print("\n");

                                        switch (j) {
                                            case 1: {
                                                CD.hoja_reeemplazar_contenido_en_substring_con_mayus_minus(search, replace);
                                                break;
                                            }
                                            case 2: {
                                                CD.hoja_reeemplazar_contenido_exacto_sin_mayus_minus(search, replace);
                                                break;
                                            }
                                            case 3: {
                                                CD.hoja_reeemplazar_contenido_exacto_con_mayus_minus(search, replace);
                                                break;
                                            }
                                            case 4: {
                                                CD.hoja_reeemplazar_contenido_en_substring_sin_mayus_minus(search, replace);
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 6: {
                                    System.out.print("Ingrese la fila de la celda que desea copiar: ");
                                    int celda_x = capt.nextInt();

                                    System.out.print("Ingrese la columna de la celda que desea copiar: ");
                                    int celda_y = capt.nextInt();

                                    if (!CD.set_celda_copiada(celda_x - 1, celda_y - 1)) {
                                        System.out.println("\nError: La celda introducida no existe.\n");
                                    }
                                    break;
                                }
                                case 7: {
                                    System.out.print("Ingrese la fila de la celda donde desea pegar: ");
                                    int celda_x = capt.nextInt();

                                    System.out.print("Ingrese la columna de la celda donde desea pegar: ");
                                    int celda_y = capt.nextInt();

                                    String contenido = CD.get_contenido_no_procesado_celda_copiada();
                                    if (contenido.equals("")) System.out.println("Error: No hay celda copiada.\n");
                                    else if (!CD.modify_contenido_celda(celda_x - 1, celda_y - 1, contenido)) {
                                        System.out.println("\nError: La celda introducida no existe.\n");
                                    }
                                    break;
                                }
                                case 8: {
                                    System.out.print("Ingrese la fila de la celda que desea modificar: ");
                                    int celda_x = capt.nextInt();

                                    System.out.print("Ingrese la columna de la celda que desea modificar: ");
                                    int celda_y = capt.nextInt();

                                    System.out.print("Ingrese contenido: ");
                                    capt.nextLine();
                                    String contenido = capt.nextLine();

                                    if (!CD.modify_contenido_celda(celda_x - 1, celda_y - 1, contenido)) {
                                        System.out.println("\nError: La celda introducida no existe.\n");
                                    } else System.out.print("\n");
                                    break;
                                }
                                case 9: {
                                    System.out.print("Ingrese la primera fila del bloque: ");
                                    int celdap_x = capt.nextInt();
                                    System.out.print("\n");

                                    System.out.print("Ingrese la primera columna del bloque: ");
                                    int celdap_y = capt.nextInt();
                                    System.out.print("\n");

                                    System.out.print("Ingrese la última fila del bloque: ");
                                    int celdau_x = capt.nextInt();
                                    System.out.print("\n");

                                    System.out.print("Ingrese la última columna del bloque: ");
                                    int celdau_y = capt.nextInt();
                                    System.out.print("\n");

                                    boolean en_bloque = false;

                                    if (CD.bloque_valido(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1)) {
                                        CD.select_bloque_actual(CD.obtener_bloque(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1));
                                        en_bloque = true;
                                    } else
                                        System.out.println("Error: El bloque debe estar dentro del rango de la hoja.\n");

                                    while (en_bloque) {
                                        System.out.println("1: Copiar/Pegar");
                                        System.out.println("2: Cortar/Pegar");
                                        System.out.println("3: Ordenar");
                                        System.out.println("4: Buscar y/o reemplazar");
                                        System.out.println("5: Media");
                                        System.out.println("6: Mediana");
                                        System.out.println("7: Varianza");
                                        System.out.println("8: Desviación");
                                        System.out.println("9: Covarianza");
                                        System.out.println("10: Coeficente de Pearson");
                                        System.out.println("11: Imprimir bloque");
                                        System.out.println("0: Volver a hoja");
                                        System.out.print("Seleccione qué desea hacer (Ingrese solo el número del comando):");
                                        int comando4 = capt.nextInt();
                                        System.out.print("\n");

                                        switch (comando4) {
                                            case 1: {
                                                System.out.print("Indique el bloque destino (debe poseer las mismas dimensiones): ");
                                                System.out.print("\n");
                                                System.out.print("Ingrese la primera fila del bloque destino: ");
                                                int x1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la primera columna del bloque destino: ");
                                                int y1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la última fila del bloque destino: ");
                                                int x2 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la última columna del bloque destino: ");
                                                int y2 = capt.nextInt();
                                                System.out.print("\n");

                                                if (CD.bloque_valido(x1 - 1, y1 - 1, x2 - 1, y2 - 1)) {
                                                    System.out.print("(\"1: Copiar contenido\");\n");
                                                    System.out.print("(\"2: Copiar valores (sin referencias ni formulas)\");\n");
                                                    System.out.print("Seleccione qué desea hacer (Ingrese solo el número del comando): \n");
                                                    int com = capt.nextInt();
                                                    System.out.print("\n");

                                                    if (com == 1) {
                                                        CD.bloque_copiar_cont(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
                                                    } else if (com == 2) {
                                                        CD.bloque_copiar_valor(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
                                                    } else {
                                                        System.out.print("(\"1: Copiar contenido\");");
                                                        System.out.print("(\"2: Copiar valores (sin referencias ni formulas)\");");
                                                        System.out.print("Seleccione qué desea hacer (Ingrese solo el número del comando): ");
                                                        com = capt.nextInt();
                                                        System.out.print("\n");
                                                    }
                                                    break;
                                                } else
                                                    System.out.println("Error: El bloque debe estar dentro del rango de la hoja.\n");
                                            }
                                            case 2: {
                                                System.out.print("Indique el bloque destino (debe poseer las mismas dimensiones): ");
                                                System.out.print("\n");
                                                System.out.print("Ingrese la primera fila del bloque destino: ");
                                                int x1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la primera columna del bloque destino: ");
                                                int y1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la última fila del bloque destino: ");
                                                int x2 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la última columna del bloque destino: ");
                                                int y2 = capt.nextInt();
                                                System.out.print("\n");

                                                if (CD.bloque_valido(x1 - 1, y1 - 1, x2 - 1, y2 - 1)) {
                                                    CD.bloque_cortar_pegar(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
                                                } else
                                                    System.out.println("Error: El bloque debe estar dentro del rango de la hoja.\n");
                                                break;
                                            }
                                            case 3: {
                                                System.out.print("Elija por qué columna ordenar: ");
                                                int col = capt.nextInt();
                                                System.out.print("\n");

                                                if (!CD.bloque_ordenar(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, col - 1))
                                                    System.out.println("Error: La columna debe estar dentro del rango de la hoja.\n");
                                                break;
                                            }
                                            case 4: {
                                                System.out.println("1: Solo buscar");
                                                System.out.println("2: Buscar y reemplazar");
                                                System.out.print("¿Desea reeemplazar también? (Ingrese solo el número): ");
                                                int i = capt.nextInt();
                                                System.out.print("\n");

                                                if (i == 1) {
                                                    System.out.print("Ingrese qué desea buscar: ");
                                                    capt.nextLine();
                                                    String search = capt.nextLine();
                                                    System.out.print("\n");

                                                    CD.bloque_busqueda(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, search);
                                                } else if (i == 2) {
                                                    System.out.print("Ingrese qué desea buscar: ");
                                                    capt.nextLine();
                                                    String search = capt.nextLine();

                                                    System.out.print("Ingrese con el cual lo desea reemplazar: ");
                                                    String replace = capt.nextLine();
                                                    System.out.print("\n");
                                                    CD.bloque_replace(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, search, replace);
                                                }
                                                break;
                                            }
                                            case 5: {
                                                double aux = CD.bloque_average(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1);
                                                System.out.println("El promedio de los valores del bloque es: " + aux + "\n");
                                                break;
                                            }
                                            case 6: {
                                                double aux = CD.bloque_median(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1);
                                                System.out.println("La mediana de los valores del bloque es: " + aux + "\n");
                                                break;
                                            }
                                            case 7: {
                                                double aux = CD.bloque_variance(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1);
                                                System.out.println("La varianza de los valores del bloque es: " + aux + "\n");
                                                break;
                                            }
                                            case 8: {
                                                double aux = CD.bloque_deviation(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1);
                                                System.out.println("El desvio estandar de los valores del bloque es: " + aux + "\n");
                                                break;
                                            }
                                            case 9: {
                                                System.out.print("Ingrese la primera fila del segundo bloque: ");
                                                int x1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la primera columna del segundo bloque: ");
                                                int y1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la última fila del segundo bloque: ");
                                                int x2 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la última columna del segundo bloque: ");
                                                int y2 = capt.nextInt();
                                                System.out.print("\n");

                                                if (CD.bloque_valido(x1 - 1, y1 - 1, x2 - 1, y2 - 1)) {
                                                    double aux = CD.bloque_covariance(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
                                                    System.out.println(aux);
                                                } else
                                                    System.out.println("Error: El bloque debe estar dentro del rango de la hoja.\n");
                                                break;
                                            }
                                            case 10: {
                                                System.out.print("Ingrese la fila de la primera celda del segundo bloque: ");
                                                int x1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la columna de la primera celda del segundo bloque: ");
                                                int y1 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la fila de la última celda del segundo bloque: ");
                                                int x2 = capt.nextInt();
                                                System.out.print("\n");

                                                System.out.print("Ingrese la columna de la última celda del segundo bloque: ");
                                                int y2 = capt.nextInt();
                                                System.out.print("\n");

                                                if (CD.bloque_valido(x1 - 1, y1 - 1, x2 - 1, y2 - 1)) {
                                                    double aux = CD.bloque_Pearson(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1, x1 - 1, y1 - 1, x2 - 1, y2 - 1);
                                                    System.out.println(aux);
                                                } else
                                                    System.out.println("Error: El bloque debe estar dentro del rango de la hoja.\n");
                                                break;
                                            }
                                            case 11: {
                                                CD.print_bloque(celdap_x - 1, celdap_y - 1, celdau_x - 1, celdau_y - 1);
                                                break;
                                            }
                                            case 0: {
                                                en_bloque = false;
                                                System.out.println("Trabajando con la hoja: " + CD.get_nombre_hoja_actual() + "\n");
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 10: {
                                    CD.print_hoja_actual();
                                    break;
                                }
                                case 0: {
                                    en_hoja = false;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 7: {
                        System.out.println("Ingresa el path del CSV: ");
                        capt.nextLine();
                        String path = capt.nextLine();
                        CP.ImportFileCSV(path);
                        break;
                    }
                    case 0: {
                        cerrar = true;
                        break;
                    }
                }
            }
            System.out.println("Documento cerrado.");
            System.out.println("¡Hasta la próxima!");
        }
    }
}
package com.manutalent.contrasenia;

import java.util.ArrayList;
import java.util.Scanner;

import com.manutalent.contrasenia.model.Categoria;
import com.manutalent.contrasenia.model.Contrasenia;
import com.manutalent.contrasenia.model.ContraseniaBancos;
import com.manutalent.contrasenia.model.ContraseniaStreaming;

public class App {

	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);

		ArrayList<Contrasenia> contrasenias = new ArrayList<>();
		ArrayList<Categoria> categorias = new ArrayList<>();
		
		precargaCategorias(categorias);

		int opcion;

		do{
			System.out.println("\n======================================================");
            System.out.println(" \t EL PEOR GESTOR DE CONTRASEÑAS DEL MUNDO");
            System.out.println("======================================================");
            System.out.println("1 - Ingresar contraseña");
            System.out.println("2 - Listar contraseñas");
            System.out.println("3 - Consultar una contraseña");
            System.out.println("4 - Modificar una contraseña");
            System.out.println("5 - Eliminar una contraseña");
            System.out.println("6 - Listar categorías de contraseñas");
            System.out.println("0 - Salir");
            System.out.println("======================================================");

			opcion = leerEntero(scanner,"Por favor ingrese una opcion: ");
			
			 switch (opcion) {
                case 1:
                    ingresarContrasenia(scanner, contrasenias, categorias);
                    break;
                case 2:
                    listarContrasenias(contrasenias);
                    break;
                case 3:
                    consultarContrasenia(scanner, contrasenias);
                    break;
                case 4:
                    modificarContrasenia(scanner, contrasenias, categorias);
                    break;
                case 5:
                    eliminarContrasenia(scanner, contrasenias);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Bon voyage,que tengas un buen dia!");
                    break;
                default:
                    System.out.println("\n Error: vamos... la opción ingresada no es válida.");
            }
		
		} while (opcion != 0);

		scanner.close();

		
	}

	// METODOS VARIOS

	// precarga
	public static void precargaCategorias(ArrayList<Categoria> categorias) {
		categorias.add(new Categoria(1, "Audiovisual", "Peliculas, series y documentales"));
		categorias.add(new Categoria(2, "Deportivas", "Suscripciones deportivas"));
		categorias.add(new Categoria(3, "Tradicional", "Bancos tradicionales"));
		categorias.add(new Categoria(4, "Fintech", "Prepagas y bancos digitales"));
	}

	// leerentero
	public static int leerEntero(Scanner scanner, String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Error: vamos... debes ingresar un número entero válido.");
			}
		}
	}

	// ingresarContrasenia
	public static void ingresarContrasenia(
			Scanner scanner,
			ArrayList<Contrasenia> contrasenias,
			ArrayList<Categoria> categorias) {
		System.out.println("\n--- INGRESAR CONTRASEÑA ---");
		System.out.println("1 - Contrasena de Streaming");
		System.out.println("2 - Contraseña Bancaria");

		int tipo;
		do {
			tipo = leerEntero(scanner, "Seleccione el tipo de contraseña: ");

			if (tipo != 1 && tipo != 2) {
				System.out.println("Error: debe elegir 1 o 2.");
			}

		} while (tipo != 1 && tipo != 2);

		int codigo = leerEntero(scanner, "Ingrese el código a asignar de la contraseña: ");


		if (buscarContraseniaPorCodigo(contrasenias, codigo) != null) {
			System.out.println("Error: ya existe una contrasenia con ese código.");
			return;
		}

		String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre de la contraseña: ");

		int password = leerEnteroNoNegativo(scanner, "Ingrese la contraseña, solo numeros positivos: ");

		listarCategorias(categorias);
		Categoria categoria = pedirCategoriaExistente(scanner, categorias);

		// Declaramos una variable de tipo Contrasenia
		// Después le asignaremos una instancia de la clase hija correspondiente.
		Contrasenia contrasenia;

		if (tipo == 1) {
			//Su info adicional
			int cantidadUsuarios = leerEnteroNoNegativo(scanner, "Ingrese la cantidad que estan usando el servicio: ");

			// Creamos un objeto de la clase hija ContraseniaStreaming
			contrasenia = new ContraseniaStreaming(codigo, nombre, password, categoria, cantidadUsuarios);
		} else {
			//Su info adicional
			int diasParaVencimiento = leerEnteroNoNegativo(scanner, "Ingrese los días para el vencimiento: ");

			// Creamos un objeto de la clase hija ContraseniaBancos.
			contrasenia = new ContraseniaBancos(codigo, nombre, password, categoria, diasParaVencimiento);
		}

		contrasenias.add(contrasenia);

		System.out.println("Contraseña ingresada correctamente.");
		System.out.println("Resumen del objeto creado:");
		System.out.println(contrasenia);
	}

	//eliminarContrasenia
	public static void eliminarContrasenia(Scanner scanner, ArrayList<Contrasenia> contrasenias) {
        System.out.println("\n--- ELIMINAR CONTRASEÑA ---");

        if (contrasenias.isEmpty()) {
            System.out.println("No hay contraseñas cargadas todavia.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código de la contraseña a eliminar: ");

        Contrasenia contrasenia = buscarContraseniaPorCodigo(contrasenias, codigo);

        if (contrasenia == null) {
            System.out.println("La contraseña no existe.");
            return;
        }

        contrasenias.remove(contrasenia);
        System.out.println("Contraseña eliminada correctamente!.");
    }

	//listarContrasenias
	// QUE ONDA CON EL PROPIO DEL NUEMERO DE TELEFONO ; CON ESO DE TRANSFORMAR era casting si

	public static void listarContrasenias(ArrayList<Contrasenia> contrasenias) {
        System.out.println("\n--- LISTA DE CONTRASEÑAS ---");

        if (contrasenias.isEmpty()) {
            System.out.println("No hay contraseñas cargadas, todavia.");
            return;
        }

        for (Contrasenia contrasenia : contrasenias) {

            System.out.println(contrasenia);
            if (contrasenia instanceof ContraseniaStreaming) {
                // tengo que transformar contrasenia en ContraseniaStreaming para acceder a su método específico
               // contrasenia.nroTelFamiliarAdministrador();
            }
           
        }
    }

	//consultarContrasenia
	public static void consultarContrasenia(Scanner scanner, ArrayList<Contrasenia> contrasenias) {
        System.out.println("\n--- CONSULTAR CONTRASEÑA ---");

        if (contrasenias.isEmpty()) {
            System.out.println("No hay contraseñas cargadas, todavia.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código de la contraseña a consultar: ");

        Contrasenia contrasenia = buscarContraseniaPorCodigo(contrasenias, codigo);

        if (contrasenia == null) {
            System.out.println("La contraseña no existe!.");
            return;
        }

        System.out.println("Contraseña encontrada:");
        System.out.println(contrasenia);
        System.out.println("Detalle específico: " + contrasenia.getDetalleEspecifico());
    }

	//modificarContrasenia
	public static void modificarContrasenia(
            Scanner scanner,
            ArrayList<Contrasenia> contrasenias,
            ArrayList<Categoria> categorias
    ) {
        System.out.println("\n--- MODIFICAR CONTRASEÑA ---");

        if (contrasenias.isEmpty()) {
            System.out.println("No hay contraseñas cargadas todavia.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código de la contraseña a modificar: ");

        Contrasenia contrasenia = buscarContraseniaPorCodigo(contrasenias, codigo);

        if (contrasenia == null) {
            System.out.println("La contraseña no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre de la contraseña: ");
        int nuevoPassword = leerEnteroNoNegativo(scanner,"Ingrese la nueva contraseña");

        listarCategorias(categorias);
        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        contrasenia.setNombre(nuevoNombre);
        contrasenia.setPassword(nuevoPassword);
        contrasenia.setCategoria(nuevaCategoria);

        // Si la contraseña es bancaria, permitimos modificar la cantidad de vencimiento.
        if (contrasenia instanceof ContraseniaBancos) {
			//Casteo
            ContraseniaBancos bancaria = (ContraseniaBancos) contrasenia;

            int nuevoVencimiento = leerEnteroNoNegativo(scanner, "Ingrese el nuevo vencimiento: ");
            bancaria.setDiasParaVencimiento(nuevoVencimiento);
        }

        // Si la contraseña es de streaming, permitimos modificar la cantidad de usuarios.
        if (contrasenia instanceof ContraseniaStreaming) {
            ContraseniaStreaming streaming = (ContraseniaStreaming) contrasenia;

            int nuevosUsuarios = leerEnteroNoNegativo(scanner, "Ingrese la nueva cantidad de usuarios : ");
            streaming.setCantidadUsuarios(nuevosUsuarios);
        }

        System.out.println("Artículo modificado correctamente.");
    }


	public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String texto = scanner.nextLine();

			if (!texto.trim().isEmpty()) {
				return texto.trim();
			}

			System.out.println("Error: el texto no puede estar vacío.");
		}
	}

	public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
		while (true) {
			int valor = leerEntero(scanner, mensaje);

			if (valor < 0) {
				System.out.println("Error: el valor no puede ser negativo.");
				continue;
			}

			return valor;
		}
	}

	public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\n--- CATEGORÍAS DE CONTRASEÑAS DISPONIBLES ---");

        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

	public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        while (true) {
            int codigoCategoria = leerEntero(scanner, "Ingrese el código de la categoría: ");

            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println("Error: la categoría no existe.");
        }
    }

	 public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

	// PARA CHEQUEAR QUE NO HAYA OTRA CON EL MISMO CODIGO (Sea unica)
	 public static Contrasenia buscarContraseniaPorCodigo(ArrayList<Contrasenia> contrasenias, int codigo) {
        for (Contrasenia contrasenia : contrasenias) {
            if (contrasenia.getCodigo() == codigo) {
                return contrasenia;
            }
        }
        return null;
    }

}

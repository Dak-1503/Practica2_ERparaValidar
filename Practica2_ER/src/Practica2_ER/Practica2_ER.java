package Practica2_ER;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




// ============================================================
//  VALIDADOR CON EXPRESIONES REGULARES
//  Valida: Correo electrónico | Teléfono (624) | Fecha dd/mm/aaaa
// ============================================================
public class Practica2_ER {

    // ----
    // MAIN
    // -----
    public static void main(String[] args) {
        menuInteractivo(); // Lanza el menú para el usuario
    }

    // ----------------------------------------------------------------
    // PATRONES (Expresiones Regulares)
    // ----------------------------------------------------------------

    /**
     * CORREO ELECTRÓNICO
     * - Uno o más caracteres antes del @  →  [a-zA-Z0-9._%+\-]+
     * - Símbolo obligatorio @             →  @
     * - Dominio                           →  [a-zA-Z0-9.\-]+
     * - Extensión obligatoria .com        →  \.com
     * - Puede terminar en .com o .com.mx  →  (\.com)([a-zA-Z.]*)?$
     */
    private static final String REGEX_CORREO =
            "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.com([a-zA-Z.]*)?$";

    /**
     * TELÉFONO LOCAL (624)
     * - Código de área obligatorio (624)  →  \(624\)
     * - Separador opcional espacio o -    →  [\\s\\-]?
     * - 3 dígitos                         →  [0-9]{3}
     * - Separador opcional                →  [\\s\\-]?
     * - 4 dígitos finales                 →  [0-9]{4}
     * Formatos válidos: (624)1234567 | (624) 123-4567 | (624)123 4567
     */
    private static final String REGEX_TELEFONO =
            "^\\(624\\)[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{4}$";

    /**
     * FECHA DE NACIMIENTO  dd/mm/aaaa
     * - Día  01-09 ó 10-19 ó 20-29 ó 30-31  →  (0[1-9]|[12][0-9]|3[01])
     * - Separador /                          →  \/
     * - Mes  01-09 ó 10-12                   →  (0[1-9]|1[0-2])
     * - Separador /                          →  \/
     * - Año  4 dígitos                       →  [0-9]{4}
     */
    private static final String REGEX_FECHA =
            "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";

    // ----------------------------------------------------------------
    // MÉTODOS DE VALIDACIÓN
    // ----------------------------------------------------------------

    public static boolean validarCorreo(String correo) {
        Pattern patron = Pattern.compile(REGEX_CORREO);
        Matcher matcher = patron.matcher(correo);
        return matcher.matches();
    }

    public static boolean validarTelefono(String telefono) {
        Pattern patron = Pattern.compile(REGEX_TELEFONO);
        Matcher matcher = patron.matcher(telefono);
        return matcher.matches();
    }

    public static boolean validarFecha(String fecha) {
        Pattern patron = Pattern.compile(REGEX_FECHA);
        Matcher matcher = patron.matcher(fecha);
        return matcher.matches();
    }

    // ----------------------------------------------------------------
    // UTILIDAD: imprime resultado con formato
    // ----------------------------------------------------------------
    private static void mostrarResultado(String tipo, String valor, boolean valido) {
        String estado = valido ? "✔  VÁLIDO" : "✘  INVÁLIDO";
        System.out.printf("  %-22s | %-30s | %s%n", tipo, valor, estado);
    }

    // ----------------------------------------------------------------
    // MENÚ INTERACTIVO
    // ----------------------------------------------------------------
    public static void menuInteractivo() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║     VALIDADOR DE DATOS CON REGEX         ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Validar Correo Electrónico           ║");
            System.out.println("║  2. Validar Teléfono (624)               ║");
            System.out.println("║  3. Validar Fecha de Nacimiento          ║");
            System.out.println("║  4. Validar los tres datos               ║");
            System.out.println("║  5. Salir                                ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("  Selecciona una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("\n  Ingresa el correo electrónico: ");
                    String correo = scanner.nextLine().trim();
                    System.out.println();
                    mostrarResultado("Correo electrónico", correo, validarCorreo(correo));
                    if (!validarCorreo(correo)) {
                        System.out.println("  -> Debe contener '@' y terminar en '.com'");
                        System.out.println("  -> Ejemplo válido: usuario@dominio.com");
                    }
                    break;

                case "2":
                    System.out.print("\n  Ingresa el teléfono: ");
                    String telefono = scanner.nextLine().trim();
                    System.out.println();
                    mostrarResultado("Teléfono (624)", telefono, validarTelefono(telefono));
                    if (!validarTelefono(telefono)) {
                        System.out.println("  -> Debe iniciar con (624) seguido de 7 dígitos");
                        System.out.println("  -> Ejemplo válido: (624)1234567 | (624) 123-4567");
                    }
                    break;

                case "3":
                    System.out.print("\n  Ingresa la fecha (dd/mm/aaaa): ");
                    String fecha = scanner.nextLine().trim();
                    System.out.println();
                    mostrarResultado("Fecha de nacimiento", fecha, validarFecha(fecha));
                    if (!validarFecha(fecha)) {
                        System.out.println("  -> Formato requerido: dd/mm/aaaa");
                        System.out.println("  -> Ejemplo válido: 15/03/1995");
                    }
                    break;

                case "4":
                    System.out.print("\n  Correo electrónico : ");
                    String c = scanner.nextLine().trim();
                    System.out.print("  Teléfono           : ");
                    String t = scanner.nextLine().trim();
                    System.out.print("  Fecha (dd/mm/aaaa) : ");
                    String f = scanner.nextLine().trim();

                    System.out.println("\n  ─────────────────────────────────────────────────────");
                    System.out.printf("  %-22s | %-30s | %s%n", "CAMPO", "VALOR", "RESULTADO");
                    System.out.println("  ─────────────────────────────────────────────────────");
                    mostrarResultado("Correo electrónico", c, validarCorreo(c));
                    mostrarResultado("Teléfono (624)",     t, validarTelefono(t));
                    mostrarResultado("Fecha de nacimiento",f, validarFecha(f));
                    System.out.println("  ─────────────────────────────────────────────────────");
                    break;

                case "5":
                    continuar = false;
                    System.out.println("\n  ¡Hasta luego!\n");
                    break;

                default:
                    System.out.println("  Opción no válida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }
}
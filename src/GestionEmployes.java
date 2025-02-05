import java.util.Arrays;
import java.util.Scanner;

public class GestionEmployes {
    // Tableau des employés (maximum 50)
    private static Employe[] employes = new Employe[50];
    private static int nbEmployes = 0;

    // Méthode pour afficher le menu principal
    public static void printMenu() {
        System.out.println("===== Gestion des Employés =====");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("8. Quitter");
    }

    // Méthode pour ajouter un employé
    public static void ajouterEmploye(Employe employe) {
        if (nbEmployes < employes.length) {
            employes[nbEmployes] = employe;
            nbEmployes++;
            System.out.println("Employé ajouté.");
        } else {
            System.out.println("Le tableau est plein.");
        }
    }

    // Méthode pour modifier un employé
    public static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        for (int i = 0; i < nbEmployes; i++) {
            if (employes[i].getId() == id) {
                employes[i].setNom(nouveauNom);
                employes[i].setPoste(nouveauPoste);
                employes[i].setSalaire(nouveauSalaire);
                System.out.println("Employé modifié.");
                return;
            }
        }
        System.out.println("Employé introuvable.");
    }

    // Méthode pour supprimer un employé
    public static void supprimerEmploye(int id) {
        for (int i = 0; i < nbEmployes; i++) {
            if (employes[i].getId() == id) {
                for (int j = i; j < nbEmployes - 1; j++) {
                    employes[j] = employes[j + 1];
                }
                employes[nbEmployes - 1] = null; // Libérer la dernière position
                nbEmployes--;
                System.out.println("Employé supprimé.");
                return;
            }
        }
        System.out.println("Employé introuvable.");
    }

    // Méthode pour afficher tous les employés
    public static void afficherEmployes() {
        if (nbEmployes == 0) {
            System.out.println("Aucun employé à afficher.");
        } else {
            for (int i = 0; i < nbEmployes; i++) {
                System.out.println(employes[i].toString());
            }
        }
    }

    // Méthode pour rechercher un employé par nom ou poste
    public static void rechercherEmploye(String critere) {
        boolean trouve = false;
        for (int i = 0; i < nbEmployes; i++) {
            if (employes[i].getNom().contains(critere) || employes[i].getPoste().contains(critere)) {
                System.out.println(employes[i].toString());
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun employé trouvé.");
        }
    }

    // Méthode pour calculer la masse salariale
    public static void calculerMasseSalariale() {
        double masseSalariale = 0.0;
        for (int i = 0; i < nbEmployes; i++) {
            masseSalariale += employes[i].getSalaire();
        }
        System.out.println("La masse salariale totale est: " + masseSalariale);
    }

    // Méthode pour trier les employés par salaire
    public static void trierEmployesParSalaire(boolean ordreCroissant) {
        Arrays.sort(employes, 0, nbEmployes, (e1, e2) -> {
            return ordreCroissant ? Employe.compareParSalaire(e1, e2) : Employe.compareParSalaire(e2, e1);
        });
        afficherEmployes();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choix = sc.nextInt();
            sc.nextLine();  // Pour consommer la nouvelle ligne après l'entier

            switch (choix) {
                case 1:
                    System.out.println("Entrez l'ID de l'employé:");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consommer la nouvelle ligne
                    System.out.println("Entrez le nom de l'employé:");
                    String nom = sc.nextLine();
                    System.out.println("Entrez le poste de l'employé:");
                    String poste = sc.nextLine();
                    System.out.println("Entrez le salaire de l'employé:");
                    double salaire = sc.nextDouble();
                    ajouterEmploye(new Employe(id, nom, poste, salaire));
                    break;

                case 2:
                    System.out.println("Entrez l'ID de l'employé à modifier:");
                    int idModif = sc.nextInt();
                    sc.nextLine(); // Consommer la nouvelle ligne
                    System.out.println("Entrez le nouveau nom de l'employé:");
                    String nomModif = sc.nextLine();
                    System.out.println("Entrez le nouveau poste de l'employé:");
                    String posteModif = sc.nextLine();
                    System.out.println("Entrez le nouveau salaire de l'employé:");
                    double salaireModif = sc.nextDouble();
                    modifierEmploye(idModif, nomModif, posteModif, salaireModif);
                    break;

                case 3:
                    System.out.println("Entrez l'ID de l'employé à supprimer:");
                    int idSuppr = sc.nextInt();
                    supprimerEmploye(idSuppr);
                    break;

                case 4:
                    afficherEmployes();
                    break;

                case 5:
                    System.out.println("Entrez un critère de recherche (nom ou poste):");
                    String critere = sc.nextLine();
                    rechercherEmploye(critere);
                    break;

                case 6:
                    calculerMasseSalariale();
                    break;

                case 7:
                    System.out.println("Ordre croissant (true) ou décroissant (false):");
                    boolean ordre = sc.nextBoolean();
                    trierEmployesParSalaire(ordre);
                    break;

                case 8:
                    running = false;
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }

        sc.close();
    }
}




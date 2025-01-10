package Programacion.Pruebas;

abstract class Animal{
    abstract void hacerSonido();
}

class Perro extends Animal{
    void hacerSonido(){
        System.out.println("Guau");
    }
}

class Gato extends Animal{
    void hacerSonido(){
        System.out.println("Miau");
    }
}

public class AnimalArray{
    public static void main (String[] args){
        Animal [] animales = new Animal[3];
        animales[0] = new Perro();
        animales[1] = new Gato();
        animales[2] = null;

        for(Animal animal : animales){
            if (animal != null){
                animal.hacerSonido();
            } else {
                System.out.println("Animal no inicializado. Es null");
            }
        }
    }

}

/*
Usar jerarquías de clases con arrays
Define una jerarquía de clases para animales (Animal, Perro, Gato).
Crea un array de tipo Animal y asigna objetos de diferentes subclases.
Itera sobre el array y llama a un metodo específico para cada tipo.
 */
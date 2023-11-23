package api.test;

import api.endpoints.UserEndPoints;
import api.payload.Dog;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static api.endpoints.UserEndPoints.getRandomDogImage;

public class DogTest {
    Dog breed;
    Dog sub;
    Dog random;
    private String subBreed;
    private String image;

    /*public DogTest(String subBreed, String image) {
        this.subBreed = subBreed;
        this.image = image;
    }*/

    @Parameters({"subBreed", "image"})

    @BeforeClass
    public void getAPIData(@Optional("") String subBreed,
                           @Optional("") String image)
    {
        breed = new Dog();
        breed.setBreed("all");

        sub = new Dog();
        sub.setSub("retriever");

        random = new Dog();
        random.setImage("golden");
    }

    @Test
    public void getAllDogByBreed() {
        Response response = UserEndPoints.getDogByBreed(this.breed.getBreed());
        response.then().log().all();
    }

    @Test
    public void verifyBreedWithinList() {
        Response response = UserEndPoints.getDogByBreed(this.breed.getBreed());
        Assert.assertTrue(response.body().prettyPrint().contains("retriever"));
        if (response.body().prettyPrint().contains("retriever")){
            System.out.println("Verify - retriever is within list");
        }
    }

    @Test
    public void getDogBySubBreed() {
        Response response = UserEndPoints.getDogBySubBreed(this.sub.getSub());
        response.body().prettyPrint();
    }

    @Test
    public void getRandomImage() {
        Response response = UserEndPoints.getRandomDogImage(this.random.getImage());
        response.body().prettyPrint();

    }
}
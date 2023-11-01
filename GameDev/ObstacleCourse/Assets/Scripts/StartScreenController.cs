using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using TMPro;
 
public class StartScreenController : MonoBehaviour
{

    public string mainSceneName = "Level 1"; // Name of your main scene
 
    private void Start()
    {
       
    }
 
    private void Update(){
    }
 
    public void Exit(){
        Application.Quit();
    }

    public void StartGame()
    {
        // Load the main scene when the Start button is clicked
        SceneManager.LoadScene(mainSceneName);
    }
}
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement; 
using TMPro;

public class Goal : MonoBehaviour
{
    string currentSceneName;
    public TMP_Text verdictText;
    // private Player playerScript;
    // public bool won;

    // Start is called before the first frame update
    void Start()
    {
        // playerScript = GetComponent<Player>();
        // won = playerScript.won;
        currentSceneName = SceneManager.GetActiveScene().name;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.layer == 8){
            //winSound.Play();
            verdictText.text = "Congratulations! Level Completed!";
            if (currentSceneName == "Level 1")
            {
                // Proceed the game to Level 2
                // Debug.log("Won val "+won);
                // won = true;
                Invoke("proceed", 1.0f);
            }
            else
            {
                // Main menu
                Invoke("main", 1.0f);
            }
        
        }
    }

    public void proceed(){
        
        SceneManager.LoadScene("Level 2");
        Debug.Log("Goal completed");
    }
 
    public void main(){
        SceneManager.LoadScene("Main");
        Debug.Log("Goal completed");
    }

}

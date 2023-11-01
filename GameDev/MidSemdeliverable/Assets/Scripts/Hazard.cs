using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hazard : MonoBehaviour
{

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    // Hazard Script
private void OnTriggerEnter(Collider other)
{
    if (other.gameObject.layer == 8)
    {
        Player player = other.GetComponent<Player>();
        if (player != null){
            // verdictText.text = "Try Again!";
            player.Die();
        }
    }
}
 

}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
        // References
    [Header("References")]
    public Transform trans;
    public Transform modelTrans;
    public CharacterController characterController;

    // Movement
    [Header("Movement")]
    [Tooltip("Units moved per second at maximum speed.")]
    public float movespeed = 24;
    
    [Tooltip("Time, in seconds, to reach maximum speed.")]
    public float timeToMaxSpeed = .26f;
    
    private float VelocityGainPerSecond { get { return movespeed / timeToMaxSpeed; } }
    
    [Tooltip("Time, in seconds, to go from maximum speed to stationary.")]
    public float timeToLoseMaxSpeed = .2f;
    
    private float VelocityLossPerSecond { get { return movespeed / timeToLoseMaxSpeed; } }
    
    [Tooltip("Multiplier for momentum when attempting to move in a direction opposite the current traveling direction.")]
    public float reverseMomentumMultiplier = 2.2f;

    private Vector3 movementVelocity = Vector3.zero;


    // Death and Respawning Variables
[Header("Death and Respawning")]
[Tooltip("How long after the player's death, in seconds, before they are respawned?")]
public float respawnWaitTime = 2f;
private bool dead = false;
private Vector3 spawnPoint;

    private Quaternion spawnRotation;

    // Method to handle movement logic
    private void Movement()
    {
    // Forward Movement (Z Axis)
    if (Input.GetKey(KeyCode.W) || Input.GetKey(KeyCode.UpArrow))
    {
        if (movementVelocity.z >= 0) 
            movementVelocity.z = Mathf.Min(movespeed, movementVelocity.z + VelocityGainPerSecond * Time.deltaTime);
        else
            movementVelocity.z = Mathf.Min(0, movementVelocity.z + VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
    }
    // Backward Movement (Z Axis)
    else if (Input.GetKey(KeyCode.S) || Input.GetKey(KeyCode.DownArrow))
    {
        if (movementVelocity.z > 0) 
            movementVelocity.z = Mathf.Max(0, movementVelocity.z - VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
        else 
            movementVelocity.z = Mathf.Max(-movespeed, movementVelocity.z - VelocityGainPerSecond * Time.deltaTime);
    }
    // If neither forward nor back are being held
    else
    {
        if (movementVelocity.z > 0) 
            movementVelocity.z = Mathf.Max(0, movementVelocity.z - VelocityLossPerSecond * Time.deltaTime);
        else 
            movementVelocity.z = Mathf.Min(0, movementVelocity.z + VelocityLossPerSecond * Time.deltaTime);
    }
    // Right Movement (X Axis)
    if (Input.GetKey(KeyCode.D) || Input.GetKey(KeyCode.RightArrow))
    {
        if (movementVelocity.x >= 0) 
            movementVelocity.x = Mathf.Min(movespeed, movementVelocity.x + VelocityGainPerSecond * Time.deltaTime);
        else 
            movementVelocity.x = Mathf.Min(0, movementVelocity.x + VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
    }
    // Left Movement (X Axis)
    else if (Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.LeftArrow))
    {
        if (movementVelocity.x > 0) 
            movementVelocity.x = Mathf.Max(0, movementVelocity.x - VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
        else 
            movementVelocity.x = Mathf.Max(-movespeed, movementVelocity.x - VelocityGainPerSecond * Time.deltaTime);
    }
    // If neither right nor left are being held
    else 
    {
        if (movementVelocity.x > 0) 
            movementVelocity.x = Mathf.Max(0, movementVelocity.x - VelocityLossPerSecond * Time.deltaTime);
        else 
            movementVelocity.x = Mathf.Min(0, movementVelocity.x + VelocityLossPerSecond * Time.deltaTime);
    }

    if (movementVelocity.x != 0 || movementVelocity.z != 0)
    {
        characterController.Move(movementVelocity * Time.deltaTime);
        modelTrans.rotation = Quaternion.Slerp(modelTrans.rotation, Quaternion.LookRotation(movementVelocity), .18F);
    }

    }


    public void Die()
    {
        if (!dead)
        {
            dead = true;
            Invoke("Respawn", respawnWaitTime);
            movementVelocity = Vector3.zero;
            enabled = false;
            characterController.enabled = false;
            modelTrans.gameObject.SetActive(false);
        }
    }

    public void Respawn()
    {
        dead = false;
        trans.position = spawnPoint;
        enabled = true;
        characterController.enabled = true;
        modelTrans.gameObject.SetActive(true);

        modelTrans.rotation = spawnRotation;
    }



    // Start is called before the first frame update
    void Start()
    {
        spawnPoint = transform.position;
        spawnRotation = modelTrans.rotation;
    }

    // Update is called once per frame
    // Call the Movement method in the Update method
    void Update()
    {
        Movement();

        if (Input.GetKeyDown(KeyCode.T)) 
            Die();

    }
}




// using System.Collections;
// using System.Collections.Generic;
// using UnityEngine;

// public class Player : MonoBehaviour
// {

//     // References
//     [Header("References")]
//     public Transform trans;
//     public Transform modelTrans;
//     public CharacterController characterController;

//     // Death and Respawning Variables
//     [Header("Death and Respawning")]
//     [Tooltip("How long after the player's death, in seconds, before they are respawned?")]
//     public float respawnWaitTime = 2f;
//     private bool dead = false;
//     private Vector3 spawnPoint;


//     // Movement
//     [Header("Movement")]
//     [Tooltip("Units moved per second at maximum speed.")]
//     public float movespeed = 24;
    
//     [Tooltip("Time, in seconds, to reach maximum speed.")]
//     public float timeToMaxSpeed = .26f;
    
//     private float VelocityGainPerSecond { get { return movespeed / timeToMaxSpeed; } }
    
//     [Tooltip("Time, in seconds, to go from maximum speed to stationary.")]
//     public float timeToLoseMaxSpeed = .2f;
    
//     private float VelocityLossPerSecond { get { return movespeed / timeToLoseMaxSpeed; } }
    
//     [Tooltip("Multiplier for momentum when attempting to move in a direction opposite the current traveling direction.")]
//     public float reverseMomentumMultiplier = 2.2f;

//     private Vector3 movementVelocity = Vector3.zero;

//     // Start is called before the first frame update
//     void Start()
//     {
//         spawnPoint = transform.position;
//     }

//         // Call the Movement method in the Update method
//     private void Update()
//     {
//         Movement();
//     }


//     private void Movement()
//     {
//         // Forward Movement (Z Axis)
//         if (Input.GetKey(KeyCode.W) || Input.GetKey(KeyCode.UpArrow))
//         {
//             if (movementVelocity.z >= 0) 
//                 movementVelocity.z = Mathf.Min(movespeed, movementVelocity.z + VelocityGainPerSecond * Time.deltaTime);
//             else
//                 movementVelocity.z = Mathf.Min(0, movementVelocity.z + VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
//         }
    
//         // Backward Movement (Z Axis)
//         else if (Input.GetKey(KeyCode.S) || Input.GetKey(KeyCode.DownArrow))
//         {
//             if (movementVelocity.z > 0) 
//                 movementVelocity.z = Mathf.Max(0, movementVelocity.z - VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
//             else 
//                 movementVelocity.z = Mathf.Max(-movespeed, movementVelocity.z - VelocityGainPerSecond * Time.deltaTime);
//         }
    
//         // If neither forward nor back are being held
//         else
//         {
//             if (movementVelocity.z > 0) 
//                 movementVelocity.z = Mathf.Max(0, movementVelocity.z - VelocityLossPerSecond * Time.deltaTime);
//             else 
//                 movementVelocity.z = Mathf.Min(0, movementVelocity.z + VelocityLossPerSecond * Time.deltaTime);
//         }
    
//         // Right Movement (X Axis)
//         if (Input.GetKey(KeyCode.D) || Input.GetKey(KeyCode.RightArrow))
//         {
//             if (movementVelocity.x >= 0) 
//                 movementVelocity.x = Mathf.Min(movespeed, movementVelocity.x + VelocityGainPerSecond * Time.deltaTime);
//             else 
//                 movementVelocity.x = Mathf.Min(0, movementVelocity.x + VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
//         }
    
//         // Left Movement (X Axis)
//         else if (Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.LeftArrow))
//         {
//             if (movementVelocity.x > 0) 
//                 movementVelocity.x = Mathf.Max(0, movementVelocity.x - VelocityGainPerSecond * reverseMomentumMultiplier * Time.deltaTime);
//             else 
//                 movementVelocity.x = Mathf.Max(-movespeed, movementVelocity.x - VelocityGainPerSecond * Time.deltaTime);
//         }
    
//         // If neither right nor left are being held
//         else 
//         {
//             if (movementVelocity.x > 0) 
//                 movementVelocity.x = Mathf.Max(0, movementVelocity.x - VelocityLossPerSecond * Time.deltaTime);
//             else 
//                 movementVelocity.x = Mathf.Min(0, movementVelocity.x + VelocityLossPerSecond * Time.deltaTime);
//         }

//         if (movementVelocity.x != 0 || movementVelocity.z != 0)
//         {
//             characterController.Move(movementVelocity * Time.deltaTime);
//             modelTrans.rotation = Quaternion.Slerp(modelTrans.rotation, Quaternion.LookRotation(movementVelocity), .18F);
//         }

//         if (Input.GetKeyDown(KeyCode.T)) 
//             Die();


//     }
    
//     public void Die()
//     {
//         if (!dead)
//         {
//             dead = true;
//             Invoke("Respawn", respawnWaitTime);
//             movementVelocity = Vector3.zero;
//             enabled = false;
//             characterController.enabled = false;
//             modelTrans.gameObject.SetActive(false);
//         }
//     }
//     public void Respawn()
//     {
//         dead = false;
//         trans.position = spawnPoint;
//         enabled = true;
//         characterController.enabled = true;
//         modelTrans.gameObject.SetActive(true);
//     }  



// }

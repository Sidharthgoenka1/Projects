package com.capstone.capstoneProject;

import com.capstone.capstoneProject.dashboard.model.InventoryMaster;
import com.capstone.capstoneProject.dashboard.model.OrderStatusMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectConstants {

//    public static String BASE_URL = "http://10.66.139.124:8080"; //home
    public static String BASE_URL = "http://10.0.0.3:8080";
//    public static String BASE_URL = "http://81.28.156.217:8080"; //cambie

    public static Map<Integer, List<OrderStatusMaster>> orderParentStatusMasterMap = new HashMap<>();
    public static Map<Integer, OrderStatusMaster> orderStatusMasterMap = new HashMap<>();
    public static Map<String, OrderStatusMaster> orderStatusNameMasterMap = new HashMap<>();
    public static List<String> rolesList = new ArrayList<>();
    public static Map<Integer, InventoryMaster> invIdMasterMap = new HashMap<>();
    public static Map<String, InventoryMaster> invNameMasterMap = new HashMap<>();
}

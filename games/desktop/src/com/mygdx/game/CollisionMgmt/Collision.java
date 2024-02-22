// package com.mygdx.game.CollisionMgmt;
// import com.mygdx.game.EntityMgmt.Entity;


// public class Collision {
//     private boolean checkCollision(Entity e1, Entity e2) {
//         // Implement collision detection logic
//         float e1Left = e1.getX();
//         float e1Right = e1.getX() + e1.getWidth();
//         float e1Top = e1.getY() + e1.getHeight();
//         float e1Bottom = e1.getY();

//         float e2Left = e2.getX();
//         float e2Right = e2.getX() + e2.getWidth();
//         float e2Top = e2.getY() + e2.getHeight();
//         float e2Bottom = e2.getY();

//         return e1Right >= e2Left && e1Left <= e2Right && e1Top >= e2Bottom && e1Bottom <= e2Top;
//     }
// }
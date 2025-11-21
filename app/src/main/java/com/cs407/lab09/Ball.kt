package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if (isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val a0x = accX
        val a1x = xAcc
        val a0y = accY
        val a1y = yAcc

        // eq 1 and 2
        val newVelocityX = velocityX + 0.5f * (a1x + a0x) * dT
        val newVelocityY = velocityY + 0.5f * (a1y + a0y) * dT
        val dx = velocityX * dT + (1f / 6f) * (3f * a0x + a1x) * dT * dT
        val dy = velocityY * dT + (1f / 6f) * (3f * a0y + a1y) * dT * dT

        posX += dx
        posY += dy
        velocityX = newVelocityX
        velocityY = newVelocityY
        accX = a1x
        accY = a1y
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        //   x--->
        // y
        // |
        // v

        // left
        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        // right
        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }

        // top
        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }

        // bot
        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        posX = backgroundWidth / 2f - ballSize / 2f
        posY = backgroundHeight / 2f - ballSize / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}
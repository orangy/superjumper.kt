package com.badlogicgames.superjumper

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment

public class WinScreen(var game: SuperJumper) : ScreenAdapter() {
    var cam: OrthographicCamera
    var princess: TextureRegion
    var messages = array("Princess: Oh dear!\n What have you done?", "Bob: I came to \nrescue you!", "Princess: you are\n mistaken\nI need no rescueing", "Bob: So all this \nwork for nothing?", "Princess: I have \ncake and tea!\nWould you like some?", "Bob: I'd be my \npleasure!", "And they ate cake\nand drank tea\nhappily ever \nafter\n\n\n\n\n\n\nKära Emma!\nDu är fantastisk!\nDu blev ferdig\n med spelet!")
    var currentMessage = 0

    {
        cam = OrthographicCamera()
        cam.setToOrtho(false, 320f, 480f)
        princess = TextureRegion(Assets.arrow.getTexture(), 210, 122, -40, 38)
    }

    override fun render(delta: Float) {
        if (Gdx.input.justTouched()) {
            currentMessage++
            if (currentMessage == messages.size) {
                currentMessage--
                game.setScreen(MainMenuScreen(game))
            }
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        cam.update()
        game.batcher.setProjectionMatrix(cam.combined)
        game.batcher.begin()
        game.batcher.draw(Assets.backgroundRegion, 0f, 0f)
        game.batcher.draw(Assets.castle, 60f, 120f, 200f, 200f)
        game.batcher.draw(Assets.bobFall.getKeyFrame(0f, Animation.Type.Looping), 120f, 200f)
        Assets.font.drawMultiLine(game.batcher, messages[currentMessage], 0f, 400f, 320f, HAlignment.CENTER)
        game.batcher.draw(princess, 150f, 200f)
        game.batcher.end()
    }
}

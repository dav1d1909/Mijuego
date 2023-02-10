package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen extends BaseScreen {

    private Stage stage;
    private Skin skin;

    private TextButton play;
    private TextButton menu;
    private Image title;

    public GameOverScreen(final MainGame game) {
        super(game);

        stage = new Stage(new FitViewport(640,320));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        title = new Image(game.manager.get("gameover.png", Texture.class));

        play = new TextButton("Retry",skin);
        menu = new TextButton("Go to menu",skin);

        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.gameScreen);
            }
        });

        menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuScreen);
            }
        });

        title.setPosition(320f-(title.getWidth()/2),220f-(title.getHeight()/2));
        play.setSize(200f,60f);
        menu.setSize(200f,60f);

        play.setPosition(320f-(play.getWidth()/2),120f-(play.getHeight()/2));
        menu.setPosition(320f-(menu.getWidth()/2),50f-(menu.getHeight()/2));

        stage.addActor(play);
        stage.addActor(menu);
        stage.addActor(title);

    }
    @Override
    public void show() {
        super.show();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();

        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        super.dispose();

        stage.dispose();
    }
}

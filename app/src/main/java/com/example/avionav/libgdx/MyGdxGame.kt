package com.example.avionav.libgdx

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Files
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.UBJsonReader
import com.example.avionav.model.Plane

/**
 * This class represent the game logic
 * @param plane: which plane need to render it is model
 */
class MyGdxGame(private val plane: Plane) : ApplicationAdapter() {

    private var cam: PerspectiveCamera? = null
    private var modelBatch: ModelBatch? = null
    private var environment: Environment? = null
    private var model: Model? = null
    private var instance: ModelInstance? = null
    private var camController: CameraInputController? = null
    private val camPosition = 100f
    private val redValue = 0.502f
    private val blueValue = 0.502f
    private val greenValue = 0.502f
    private val alphaValue = 1f
    private var modelFilePath = "o57r/model.g3db"



    override fun create() {
        modelFilePath = "${plane.id}/model.g3db"
        modelBatch = ModelBatch()
        environment = Environment()
        light()
        cameraPositioning()
        cameraControl()
        instantiateModel()
    }

    override fun render() {
        ScreenUtils.clear(redValue, greenValue, blueValue, alphaValue)
        camController!!.update()
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        modelBatch!!.begin(cam)
        if (instance != null)
            modelBatch!!.render(instance, environment)
        modelBatch!!.end()
    }

    private fun instantiateModel() {
        val jsonReader = UBJsonReader()
        val modelLoader = G3dModelLoader(jsonReader)
        model =
            modelLoader.loadModel(Gdx.files.getFileHandle(modelFilePath, Files.FileType.Internal))
        instance = ModelInstance(model)
    }


    private fun cameraPositioning() {
        cam = PerspectiveCamera(
            67F, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()
        )
        cam!!.position[camPosition, camPosition] = camPosition
        cam!!.lookAt(0f, 0f, 0f)
        cam!!.near = 1f
        cam!!.far = 300f
        cam!!.update()
    }

    private fun light() {
        environment!!.set(
            ColorAttribute(
                ColorAttribute.AmbientLight,
                0.4f, 0.4f, 0.4f, 1f
            )
        )
        environment!!.add(
            DirectionalLight().set(
                0.8f,
                0.8f, 0.8f, -1f, -0.8f, -0.2f
            )
        )
    }

    private fun cameraControl() {
        camController = CameraInputController(cam)
        Gdx.input.inputProcessor = camController
    }

    override fun dispose() {
        modelBatch!!.dispose()
        model!!.dispose()
    }

}
package com.cocoraid;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ModelLoader {



    public static List<Vector> loadModel(InputStream file) throws IOException {

        try (InputStream inputStream = file) {
            Obj obj = ObjReader.read(inputStream);
            Obj normalizedObj = ObjUtils.convertToRenderable(obj);
            List<Vector> vertices = new ArrayList<>();

            for (int i = 0; i < normalizedObj.getNumVertices(); i++) {
                float x = normalizedObj.getVertex(i).getX();
                float y = normalizedObj.getVertex(i).getY();
                float z = normalizedObj.getVertex(i).getZ();
                vertices.add(new Vector(x, y, z));
            }

            return vertices;
        }
    }

}

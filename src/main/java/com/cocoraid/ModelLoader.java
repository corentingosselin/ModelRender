package com.cocoraid;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelLoader {

    public static List<Vector> loadModel(InputStream file) throws IOException {

        try (InputStream inputStream = file) {
            Obj obj = ObjReader.read(inputStream);
            Obj normalizedObj = ObjUtils.convertToRenderable(obj);
            List<Vector> vertices = new ArrayList<>();

            // Create a Set to store unique indices of surface vertices
            Set<Integer> surfaceVertexIndices = new HashSet<>();

            // Iterate over all faces and add vertex indices to the Set
            for (int i = 0; i < normalizedObj.getNumFaces(); i++) {
                for (int j = 0; j < normalizedObj.getFace(i).getNumVertices(); j++) {
                    surfaceVertexIndices.add(normalizedObj.getFace(i).getVertexIndex(j));
                }
            }

            // Iterate over the unique surface vertex indices and add the corresponding vertices to the List
            for (int index : surfaceVertexIndices) {
                float x = normalizedObj.getVertex(index).getX();
                float y = normalizedObj.getVertex(index).getY();
                float z = normalizedObj.getVertex(index).getZ();
                vertices.add(new Vector(x, y, z));
            }

            return vertices;
        }
    }

}

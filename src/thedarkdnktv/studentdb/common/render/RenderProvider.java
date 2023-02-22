package thedarkdnktv.studentdb.common.render;

import thedarkdnktv.studentdb.api.IRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RenderProvider {

    private final Map<Class<?>, IRenderer<?>> renderers;

    public RenderProvider() {
        this.renderers = new HashMap<>();
    }

    public <T> void register(Class<T> type, IRenderer<T> renderer) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(renderer);
        if (!renderers.containsKey(type)) {
            renderers.put(type, renderer);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> IRenderer<T> getRenderer(Class<T> type) {
        IRenderer<?> renderer = renderers.get(type);
        if (renderer != null && renderer.isSupported(type)) {
            return (IRenderer<T>) renderer;
        }

        return null;
    }
}

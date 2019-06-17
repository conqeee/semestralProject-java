package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.comparator.VertexInverseComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Marek Sabo
 */
public class LabeledPolygonTest {

    private LabeledPolygon polygon;

    @Before
    public void setUp() throws Exception {
        polygon = new LabeledPolygon();
    }

    @Test
    public void checkNumVertices() {
        polygon.addVertex("B", new Vertex2D(1, 5));
        assertThat(polygon.getNumVertices()).isEqualTo(1);
        polygon.addVertex("A", new Vertex2D(0, 0));
        assertThat(polygon.getNumVertices()).isEqualTo(2);
    }

    @Test
    public void puttingSamePolygonTwice() {
        // when
        polygon.addVertex("B", new Vertex2D(1, 5));
        polygon.addVertex("B", new Vertex2D(1, 5));
        // then
        assertThat(polygon.getNumVertices()).isEqualTo(1);
    }


    @Test
    public void getVertexIndexOutOfRangeNegative() {
        // when
        polygon.addVertex("A", new Vertex2D(4, 2));
        // then
        assertThatThrownBy(() -> polygon.getVertex(-1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> polygon.getVertex(-523))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getVertexIndexOutOfRangePositive() {
        // when
        polygon.addVertex("A", new Vertex2D(4, 2));
        polygon.addVertex("B", new Vertex2D(5.2, 1));
        // then
        assertThat(polygon.getVertex(0)).isEqualTo(new Vertex2D(4, 2));
        assertThat(polygon.getVertex(1)).isEqualTo(new Vertex2D(5.2, 1));
        assertThat(polygon.getVertex(2)).isEqualTo(new Vertex2D(4, 2));
        assertThat(polygon.getVertex(3)).isEqualTo(new Vertex2D(5.2, 1));
        assertThat(polygon.getVertex(4)).isEqualTo(new Vertex2D(4, 2));
    }

    @Test
    public void getVertexIndexInRange() {
        // when
        polygon.addVertex("A", new Vertex2D(0, 0));
        // then
        assertThat(polygon.getVertex(0)).isEqualTo(new Vertex2D(0, 0));
    }

    @Test
    public void getVertexIndexInRangeMultiple() {
        // when
        polygon.addVertex("B", new Vertex2D(0, 2));
        polygon.addVertex("A", new Vertex2D(1, 0));
        // then
        assertThat(polygon.getVertex(0)).isEqualTo(new Vertex2D(1, 0));
        assertThat(polygon.getVertex(1)).isEqualTo(new Vertex2D(0, 2));
        assertThat(polygon.getVertex(2)).isEqualTo(new Vertex2D(1, 0));
        assertThat(polygon.getVertex(3)).isEqualTo(new Vertex2D(0, 2));
    }

    @Test
    public void getVertices() {
        // when
        polygon.addVertex("A", new Vertex2D(0, 0));
        polygon.addVertex("C", new Vertex2D(1, 4));
        polygon.addVertex("B", new Vertex2D(7, 3));
        // then
        assertThat(polygon.getVertices())
                .containsExactlyInAnyOrder(
                        new Vertex2D(0, 0),
                        new Vertex2D(1, 4),
                        new Vertex2D(7, 3)
                );
        assertNewOrUnmodifiableCollection(polygon, polygon.getVertices());
    }

    @Test
    public void addVertexWithNull() {
        assertThatThrownBy(() -> polygon.addVertex(null, null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> polygon.addVertex("A", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("vertex");
        assertThatThrownBy(() -> polygon.addVertex(null, new Vertex2D(1, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("label");
    }

    @Test
    public void getVertexLabelNull() {
        assertThatThrownBy(() -> polygon.getVertex(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void getVertexLabel() {
        // when
        polygon.addVertex("A", new Vertex2D(0, 0));
        // then
        assertThat(polygon.getVertex("A")).isEqualTo(new Vertex2D(0, 0));
    }

    @Test
    public void checkGetAllLabels() {
        // when
        polygon.addVertex("A", new Vertex2D(0, 0));
        polygon.addVertex("C", new Vertex2D(1, 4));
        polygon.addVertex("A", new Vertex2D(0, 2));
        polygon.addVertex("C", new Vertex2D(1, 4));
        polygon.addVertex("B", new Vertex2D(7, 3));
        // then
        assertThat(polygon.getLabels()).containsExactly("A", "B", "C");
        assertNewOrUnmodifiableCollection(polygon, polygon.getLabels());
    }

    @Test
    public void checkGetLabelsWithNonExistingVertex() {
        // when
        polygon.addVertex("A", new Vertex2D(0, 0));
        // then
        assertThat(polygon.getLabels(new Vertex2D(0, 42))).isEmpty();
        assertThat(polygon.getLabels(new Vertex2D(1, 0))).isEmpty();
    }

    @Test
    public void checkGetLabelsWithExistingVertex() {
        // when
        polygon.addVertex("B", new Vertex2D(1, 1));
        polygon.addVertex("B", new Vertex2D(0, 0));
        polygon.addVertex("C", new Vertex2D(1, 4));
        polygon.addVertex("A", new Vertex2D(0, 0));
        // then
        assertThat(polygon.getLabels(new Vertex2D(0, 0)))
                .containsExactly("A", "B");
        assertThat(polygon.getLabels(new Vertex2D(1, 4)))
                .containsExactly("C");
        assertNewOrUnmodifiableCollection(polygon, polygon.getLabels(new Vertex2D(0, 0)));
    }

    private void assertNewOrUnmodifiableCollection(Polygon polygon, Collection<?> collection) {
        int expectedSize = polygon.getNumVertices();
        try {
            collection.clear();
            assertThat(polygon.getNumVertices())
                    .as("Method returns modifiable collection - return new or unmodifiable.")
                    .isEqualTo(expectedSize);
        } catch (UnsupportedOperationException e) {
            // ok
        }
    }

    @Test
    public void getSortedVertices() {
        // when
        polygon.addVertex("B", new Vertex2D(1, 0));
        polygon.addVertex("C", new Vertex2D(1, 4));
        polygon.addVertex("A", new Vertex2D(0, 0));
        // then
        assertThat(polygon.getSortedVertices())
                .containsExactly(
                        new Vertex2D(0, 0),
                        new Vertex2D(1, 0),
                        new Vertex2D(1, 4)
                );
    }

    @Test
    public void getSortedVerticesInverseComparator() {
        // given
        Comparator<Vertex2D> comparator = new VertexInverseComparator();
        // when
        polygon.addVertex("B", new Vertex2D(1, 0));
        polygon.addVertex("C", new Vertex2D(1, 4));
        polygon.addVertex("A", new Vertex2D(0, 0));
        // then
        assertThat(polygon.getSortedVertices(comparator))
                .containsExactly(
                        new Vertex2D(1, 4),
                        new Vertex2D(1, 0),
                        new Vertex2D(0, 0)
                );
    }

    @Test
    public void duplicateVertices() {
        // when
        polygon.addVertex("A", new Vertex2D(-20, 20));
        polygon.addVertex("B", new Vertex2D(100, 100));
        polygon.addVertex("C", new Vertex2D(-100, -100));
        polygon.addVertex("D", new Vertex2D(-20, 20));
        polygon.addVertex("E", new Vertex2D(-100, 100));
        polygon.addVertex("F", new Vertex2D(100, -100));
        polygon.addVertex("G", new Vertex2D(0, 0));
        polygon.addVertex("H", new Vertex2D(0, 0));
        // then
        assertThat(polygon.duplicateVertices())
                .containsExactlyInAnyOrder(
                        new Vertex2D(-20, 20),
                        new Vertex2D(0, 0)
                );
    }
}

package org.carlspring.strongbox.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.carlspring.strongbox.artifact.coordinates.GenericArtifactCoordinates;
import org.carlspring.strongbox.data.domain.DomainEntity;
import org.carlspring.strongbox.db.schema.Edges;
import org.carlspring.strongbox.db.schema.Vertices;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(Vertices.GENERIC_ARTIFACT_COORDINATES)
public class GenericArtifactCoordinatesEntity extends DomainEntity implements GenericArtifactCoordinates
{
    private String version;
    private final Map<String, String> coordinates = new LinkedHashMap<>();
    @Relationship(type = Edges.ARTIFACT_COORDINATES_INHERIT_GENERIC_ARTIFACT_COORDINATES, direction = Relationship.INCOMING)
    private LayoutArtifactCoordinatesEntity layoutArtifactCoordinates;

    public LayoutArtifactCoordinatesEntity getLayoutArtifactCoordinates()
    {
        return layoutArtifactCoordinates;
    }

    public void setLayoutArtifactCoordinates(LayoutArtifactCoordinatesEntity layoutArtifactCoordinates)
    {
        this.layoutArtifactCoordinates = layoutArtifactCoordinates;
    }

    protected void resetCoordinates(String... coordinates)
    {
        this.coordinates.clear();
        Arrays.stream(coordinates).forEach(this::defineCoordinate);
    }

    protected void defineCoordinate(String coordinate)
    {
        coordinates.put(coordinate, null);
    }

    protected String getCoordinate(String coordinate)
    {
        return coordinates.get(coordinate);
    }

    protected String setCoordinate(String coordinate,
                                   String value)
    {
        return coordinates.put(coordinate, value);
    }

    @Override
    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public Map<String, String> getCoordinates()
    {
        return coordinates;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof GenericArtifactCoordinatesEntity))
        {
            return false;
        }

        GenericArtifactCoordinatesEntity c = (GenericArtifactCoordinatesEntity) obj;
        return c.coordinates.equals(coordinates);
    }

    @Override
    public int hashCode()
    {
        return coordinates.hashCode();
    }

}
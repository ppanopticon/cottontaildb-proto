package org.vitrivr.cottontail.client.language.ddl

import org.vitrivr.cottontail.client.language.extensions.parseColumn
import org.vitrivr.cottontail.client.language.extensions.parseIndex
import org.vitrivr.cottontail.grpc.CottontailGrpc

/**
 * A CREATE INDEX query in the Cottontail DB query language.
 *
 * @author Ralph Gasser
 * @version 1.0.0
 */
class CreateIndex(name: String, type: CottontailGrpc.IndexType) {

    val builder = CottontailGrpc.CreateIndexMessage.newBuilder()

    init {
        this.builder.definitionBuilder.name = name.parseIndex()
        this.builder.definitionBuilder.type = type
    }

    /**
     * Adds a column to this [CreateIndex].
     *
     * @param column The name of the column
     * @return this [CreateIndex]
     */
    fun column(column: String): CreateIndex {
        this.builder.definitionBuilder.addColumns(column.parseColumn())
        return this
    }

    /**
     * Adds a index creation parameter to this [CreateIndex].
     *
     * @param key The name of the parameter
     * @param value The value of the parameter
     * @return this [CreateIndex]
     */
    fun param(key: String, value: Any): CreateIndex {
        this.builder.definitionBuilder.putParams(key, value.toString())
        return this
    }


    /**
     * Sets the rebuild flag of this [CreateIndex].
     *
     * @return this [CreateIndex]
     */
    fun rebuild(): CreateIndex {
        this.builder.rebuild = true
        return this
    }
}
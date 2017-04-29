package de.metas.ui.web.view.descriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.ad.expression.api.IStringExpression;

import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlEntityFieldBinding;
import lombok.Builder;
import lombok.NonNull;

/*
 * #%L
 * metasfresh-webui-api
 * %%
 * Copyright (C) 2017 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public class SqlViewRowFieldBinding implements SqlEntityFieldBinding
{
	/**
	 * Retrieves a particular field from given {@link ResultSet}.
	 */
	@FunctionalInterface
	public static interface SqlViewRowFieldLoader
	{
		Object retrieveValueAsJson(ResultSet rs) throws SQLException;
	}

	private final String fieldName;
	private final String columnName;
	private final String columnSql;
	private final boolean keyColumn;
	private final DocumentFieldWidgetType widgetType;

	private final Class<?> sqlValueClass;
	private final String sqlSelectValue;
	private final IStringExpression sqlSelectDisplayValue;
	private final boolean usingDisplayColumn;

	private final IStringExpression sqlOrderBy;

	private final SqlViewRowFieldLoader fieldLoader;

	@Builder
	private SqlViewRowFieldBinding(
			@NonNull final String fieldName //
			, @NonNull final String columnName //
			, @NonNull final String columnSql //
			, final boolean keyColumn //
			, @NonNull final DocumentFieldWidgetType widgetType //
			//
			, @NonNull final Class<?> sqlValueClass //
			, @NonNull final String sqlSelectValue //
			, @NonNull final IStringExpression sqlSelectDisplayValue //
			, final boolean usingDisplayColumn //
			//
			, @NonNull final IStringExpression sqlOrderBy //
			, @NonNull final SqlViewRowFieldLoader fieldLoader //
	)
	{
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.columnSql = columnSql;
		this.keyColumn = keyColumn;
		this.widgetType = widgetType;

		this.sqlValueClass = sqlValueClass;
		this.sqlSelectValue = sqlSelectValue;
		this.sqlSelectDisplayValue = sqlSelectDisplayValue;
		this.usingDisplayColumn = usingDisplayColumn;

		this.sqlOrderBy = sqlOrderBy;
		this.fieldLoader = fieldLoader;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	@Override
	public String getColumnName()
	{
		return columnName;
	}

	public boolean isKeyColumn()
	{
		return keyColumn;
	}

	@Override
	public String getColumnSql()
	{
		return columnSql;
	}

	@Override
	public DocumentFieldWidgetType getWidgetType()
	{
		return widgetType;
	}

	@Override
	public Class<?> getSqlValueClass()
	{
		return sqlValueClass;
	}

	public String getSqlSelectValue()
	{
		return sqlSelectValue;
	}

	public boolean isUsingDisplayColumn()
	{
		return usingDisplayColumn;
	}

	public IStringExpression getSqlSelectDisplayValue()
	{
		return sqlSelectDisplayValue;
	}

	public SqlViewRowFieldLoader getFieldLoader()
	{
		return fieldLoader;
	}

	@Override
	public IStringExpression getSqlOrderBy()
	{
		return sqlOrderBy;
	}
}

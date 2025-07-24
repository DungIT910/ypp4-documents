USE MsList

SELECT col.id, col.ColumnName, col.DataTypeId, DataType.DisplayName, stvl.ColumnId, stvl.Key	SettingId, kst.ValueType, stvl.KeyValue
FROM DynamicColumn col
INNER JOIN DataType ON col.DataTypeId = DataType.Id
INNER JOIN ColumnSettingValue stvl ON col.Id = stvl.ColumnId
INNER JOIN KeySetting kst ON stvl.KeySettingId = kst.Id

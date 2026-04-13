import { Color, ScaleType } from '@swimlane/ngx-charts';

/**
 * IMS v2 · PDF-aligned pastel analytics palette (fintech / soft donut + bar + line)
 */
export const IMS_CHART_SCHEME: Color = {
  name: 'imsModern',
  selectable: true,
  group: ScaleType.Ordinal,
  domain: [
    '#6366F1',  // Indigo - primary
    '#10B981',  // Emerald - success
    '#F59E0B',  // Amber - warning
    '#EF4444',  // Red - danger
    '#8B5CF6',  // Purple
    '#06B6D4',  // Cyan
    '#F97316',  // Orange
    '#84CC16',  // Lime
  ],
};

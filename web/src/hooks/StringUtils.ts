export function str2MoneyStr(str: string): string {
  return (parseFloat(str) * 100).toString();
}

export function str2Money(str: string): number {
  return parseFloat(str) * 100;
}

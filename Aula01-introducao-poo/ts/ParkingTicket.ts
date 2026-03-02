export class ParkingTicket {
  private readonly entryTime: Date;
  private exitTime?: Date;
  public static readonly HOURLY_RATE = 6.0;

  constructor(entryTime: Date) {
    if (!entryTime) {
      throw new Error('Entry time is required.');
    }
    this.entryTime = entryTime;
  }

  public close(exitTime: Date): void {
    this.exitTime = ParkingTicket.validateExitTime(this.entryTime, exitTime);
  }

  public calculateTotal(exitTime?: Date): number {
    const finalExit = exitTime ?? this.exitTime;
    const validatedExit = ParkingTicket.validateExitTime(this.entryTime, finalExit);
    const hours = ParkingTicket.hoursRoundedUp(this.entryTime, validatedExit);
    return hours * ParkingTicket.HOURLY_RATE;
  }

  public getEntryTime(): Date {
    return new Date(this.entryTime.getTime());
  }

  public getExitTime(): Date | undefined {
    return this.exitTime ? new Date(this.exitTime.getTime()) : undefined;
  }

  private static validateExitTime(entry: Date, exitTime?: Date): Date {
    if (!exitTime) {
      throw new Error('Exit time is required.');
    }
    if (exitTime.getTime() <= entry.getTime()) {
      throw new Error('Exit time must be after entry time.');
    }
    return exitTime;
  }

  private static hoursRoundedUp(entry: Date, exitTime: Date): number {
    const minutes = (exitTime.getTime() - entry.getTime()) / 60000;
    return Math.ceil(minutes / 60);
  }
}

import { ParkingTicket } from './ParkingTicket';

const entryTime = new Date(2026, 1, 23, 8, 10, 0);
const exitTime = new Date(2026, 1, 23, 11, 45, 0);

const ticket = new ParkingTicket(entryTime);
ticket.close(exitTime);

const total = ticket.calculateTotal();
console.log(`Total to pay: ${total.toFixed(2)}`);

import { fetchUser } from '../../services/user.service';
import { User } from '../../models/user';

global.fetch = jest.fn();

describe('fetchUser', () => {
  it('should fetch and return a User instance', async () => {
    const mockUserData = {
      id: 1,
      name: 'Max Mustermann',
      email: 'max@example.com',
      passwort: 'geheim',
      adresse: 'Musterstraße 1',
      geburtsdatum: '1990-01-01T00:00:00.000Z',
      regiestrirungsdatum: '2023-01-01T00:00:00.000Z',
      telefonnummer: '123456789',
    };

    (fetch as jest.Mock).mockResolvedValue({
      json: jest.fn().mockResolvedValue(mockUserData),
    });

    const result = await fetchUser(1);

    expect(result).toBeInstanceOf(User);
    expect(result.email).toBe('max@example.com');
  });
});

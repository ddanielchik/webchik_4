import { render, screen } from '@testing-library/react';
import App from './App';
import Test from "./Test";

test('renders learn react link', () => {
  render(<Test />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
